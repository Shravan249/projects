package com.visiontech.DTOS;

import lombok.Data;

@Data
public class SignupDto {

	private String firstName;
	private String lastName;
	private String email;
	private String password;
}
