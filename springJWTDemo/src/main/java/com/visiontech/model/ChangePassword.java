package com.visiontech.model;

import lombok.Data;

@Data
public class ChangePassword {

	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
}
