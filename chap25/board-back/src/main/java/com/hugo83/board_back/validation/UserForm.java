package com.hugo83.board_back.validation;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserForm {
	@NotEmpty(message = "이메일은 필수항목입니다.")
	private String email;

	@NotEmpty(message = "유저명은 필수항목입니다.")
	@Size(max = 30)
	private String username;

	@NotEmpty(message = "비밀번호는 필수항목입니다.")
	private String password1;

	@NotEmpty(message = "비밀번호 확인은 필수항목입니다.")
	private String password2;
}
