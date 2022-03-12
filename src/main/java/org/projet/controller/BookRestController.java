package org.projet.controller;


import java.util.List;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	@Autowired
	BookEntityRepository bookEntityRepository;
	
	@Autowired
	BookRefEntityRepository bookRefEntityRepository;
	
	@GetMapping("/allbooks")
	public List <BookEntity> findAllBooks(){
		return bookEntityRepository.findAll();
	}
	
	@GetMapping("/{author}")
	public List <BookRefEntity> findByAuthor(@PathVariable String author ){
		return bookRefEntityRepository.findByAuthorIgnoreCase(author);
	}
	
	
	
	
	

}
