package com.hugo83.b01.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.hugo83.b01.dto.upload.UploadFileDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@RestController
@Log4j2
public class UpDownController {

	@Value("${com.hugo83.upload.path}") // import 시에 springframework으로 시작하는 Value
	private String uploadPath;

	// 어떻게 바꿔야 하는지 알았다!!
	@PostMapping(value = "/upload", consumes = {
			MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<Void> uploadFile(@RequestPart List<MultipartFile> multipartFile) {
		// yaay!
		return ResponseEntity.ok(null);
	}
}
