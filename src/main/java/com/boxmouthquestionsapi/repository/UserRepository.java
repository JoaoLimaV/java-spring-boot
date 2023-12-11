package com.boxmouthquestionsapi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.boxmouthquestionsapi.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	 UserDetails findByEmail(String email);
}
