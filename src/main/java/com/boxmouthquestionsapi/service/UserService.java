package com.boxmouthquestionsapi.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	public UserDetails findByEmail(String email);
}
