package com.boxmouthquestionsapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boxmouthquestionsapi.dto.AutheticationDTO;
import com.boxmouthquestionsapi.dto.LoginResponseDTO;
import com.boxmouthquestionsapi.dto.RegisterDTO;
import com.boxmouthquestionsapi.impl.TokenService;
import com.boxmouthquestionsapi.model.User;
import com.boxmouthquestionsapi.repository.UserRepository;

@RestController
@RequestMapping("auth")
public class AuthenticationController {
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired 
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AutheticationDTO data) {	
		  try {
		        var usernamePassword = new UsernamePasswordAuthenticationToken(data.getLogin(), data.getPassword());
		        var auth = this.authManager.authenticate(usernamePassword);

		        var token = tokenService.generateToken((User) auth.getPrincipal());

		        return ResponseEntity.ok(new LoginResponseDTO(token));
		    } catch (BadCredentialsException ex) {
		    	String mensagemErro = "Credenciais de login inválidas";
	            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"" + mensagemErro + "\"}");
		    }
	}
	
	
	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {	
		if(this.userRepository.findByEmail(data.getEmail()) != null ) return ResponseEntity.badRequest().build();
		
		String encrypetedPassword = new BCryptPasswordEncoder().encode(data.getPassword());
		User newUser = new User(data.getEmail(), encrypetedPassword, data.getRole());
		
		this.userRepository.save(newUser);
		
		return ResponseEntity.ok().build();
	}
}
