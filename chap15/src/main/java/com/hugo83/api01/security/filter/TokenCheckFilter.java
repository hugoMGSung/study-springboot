package com.hugo83.api01.security.filter;

import java.io.IOException;
import java.util.Map;

import org.springframework.web.filter.OncePerRequestFilter;

import com.hugo83.api01.security.APIUserDetailsService;
import com.hugo83.api01.security.exception.AccessTokenException;
import com.hugo83.api01.util.JWTUtil;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
// @RequiredArgsConstructor
public class TokenCheckFilter extends OncePerRequestFilter {
	private final APIUserDetailsService apiUserDetailsService;
	private JWTUtil jwtUtil;

	public TokenCheckFilter(APIUserDetailsService apiUserDetailsService, JWTUtil jwtUtil) {
		this.apiUserDetailsService = apiUserDetailsService;
		this.jwtUtil = jwtUtil;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();

		if (!path.startsWith("/api/")) {
			filterChain.doFilter(request, response);
			return;
		}

		log.info("Token Check Filter..........................");
		log.info("JWTUtil: " + jwtUtil);

		try {
			validateAccessToken(request);
			filterChain.doFilter(request, response);
		} catch (AccessTokenException accessTokenException) {
			accessTokenException.sendResponseError(response);
		}
	}

	private Map<String, Object> validateAccessToken(HttpServletRequest request) throws AccessTokenException {
		String headerStr = request.getHeader("Authorization");

		if (headerStr == null || headerStr.length() < 8) {
			throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.UNACCEPT);
		}

		// Bearer 생략
		String tokenType = headerStr.substring(0, 6);
		String tokenStr = headerStr.substring(7);

		if (tokenType.equalsIgnoreCase("Bearer") == false) {
			throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADTYPE);
		}

		try {
			Map<String, Object> values = jwtUtil.validateToken(tokenStr);
			return values;
		} catch (SignatureException signatureException) {
			log.error("SignatureException----------------------");
			throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.BADSIGN);
		} catch (MalformedJwtException malformedJwtException) {
			log.error("MalformedJwtException----------------------");
			throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.MALFORM);
		} catch (ExpiredJwtException expiredJwtException) {
			log.error("ExpiredJwtException----------------------");
			throw new AccessTokenException(AccessTokenException.TOKEN_ERROR.EXPIRED);
		}
	}
}
