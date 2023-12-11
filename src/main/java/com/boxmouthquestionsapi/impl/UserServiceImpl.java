package com.boxmouthquestionsapi.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxmouthquestionsapi.model.User;
import com.boxmouthquestionsapi.repository.UserRepository;
import com.boxmouthquestionsapi.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public User findByEmail(String email) {
        return (User) this.userRepository.findByEmail(email);
    }
}
