package com.hugo83.api01.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class APIUser {
	@Id
	private String mid;
	private String mpw;

	public void changePw(String mpw) {
		this.mpw = mpw;
	}
}
