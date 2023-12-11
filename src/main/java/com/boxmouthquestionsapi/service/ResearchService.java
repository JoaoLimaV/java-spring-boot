package com.boxmouthquestionsapi.service;

import java.util.List;

import com.boxmouthquestionsapi.model.Research;

public interface ResearchService {
	public List<Research> findAll(); 
	public Research save(String idUser,Research research);
	public Research findById(String code);
	public List<Research> findAllByUser(String user);
}
