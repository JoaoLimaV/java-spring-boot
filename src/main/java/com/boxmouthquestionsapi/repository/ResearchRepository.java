package com.boxmouthquestionsapi.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.boxmouthquestionsapi.model.Research;

public interface ResearchRepository extends MongoRepository<Research, String> {
	List<Research> findAllByUser(String user);
}
