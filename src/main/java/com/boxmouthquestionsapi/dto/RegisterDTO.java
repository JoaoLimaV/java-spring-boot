package com.boxmouthquestionsapi.dto;

import com.boxmouthquestionsapi.enums.UserRole;

public class RegisterDTO {
	private String email;
	private String password;
	private UserRole role;
	
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public UserRole getRole() {
		return role;
	}
}
