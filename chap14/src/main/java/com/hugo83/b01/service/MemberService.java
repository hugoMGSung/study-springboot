package com.hugo83.b01.service;

import com.hugo83.b01.dto.MemberJoinDTO;

public interface MemberService {
	static class MidExistException extends Exception {

	}

	void join(MemberJoinDTO memberJoinDTO) throws MidExistException;
}