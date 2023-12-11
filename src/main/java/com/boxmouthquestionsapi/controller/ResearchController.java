package com.boxmouthquestionsapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.boxmouthquestionsapi.impl.TokenService;
import com.boxmouthquestionsapi.model.Research;
import com.boxmouthquestionsapi.service.ResearchService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/research")

public class ResearchController {
	@Autowired
	private ResearchService researchService;
	
	@Autowired
	private TokenService tokenService;
	
	@GetMapping("/findall")
	public List<Research> findAll() {
		return this.researchService.findAll();
	}
	
	@GetMapping("/findbyid")
	public Research findById(@RequestParam String id) {
		return this.researchService.findById(id);
	}
	
	@GetMapping("/findallbyuser")
	public List<Research> findById(HttpServletRequest request) {
		return this.researchService.findAllByUser(tokenService.getUserIdFromToken(request));
	}
	
	@PostMapping("/save")
	public Research save(HttpServletRequest request, @RequestBody Research research) {
        return this.researchService.save(tokenService.getUserIdFromToken(request), research);
	}
}
