package com.boxmouthquestionsapi.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.boxmouthquestionsapi.model.Research;
import com.boxmouthquestionsapi.model.User;
import com.boxmouthquestionsapi.repository.ResearchRepository;
import com.boxmouthquestionsapi.repository.UserRepository;
import com.boxmouthquestionsapi.service.ResearchService;

@Service
public class ResearchServiceImpl implements ResearchService {
	@Autowired
	private ResearchRepository researchRepository;
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Research> findAll() {
		return this.researchRepository.findAll();
	}

	@Override
	public Research save(String idUser, Research research) {
		
		User user = this.userRepository.findById(idUser)
				.orElseThrow(() -> new  IllegalArgumentException("Usuário Inexistente"));
		
		research.setUser(user);
		
		return this.researchRepository.save(research);
	}
	
	@Override
	public Research findById(String code) {
		return this.researchRepository.findById(code)
				.orElseThrow(() -> new  IllegalArgumentException("Pesquisa Inexistente"));
	}

	@Override
	public List<Research> findAllByUser(String idUser) {
		return this.researchRepository.findAllByUser(idUser);			
//				.orElseThrow(() -> new  IllegalArgumentException("Pesquisa Inexistente"));
	}

}
