package org.projet.controller;


import java.util.List;

import org.projet.model.Book;
import org.projet.model.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {
	
	@Autowired
	BookRepository bookRepository;
	
	@GetMapping
	public List <Book> findAllBooks(){
		return bookRepository.findAll();
	}
	
	@GetMapping("/{author}")
	public List <Book> findByAuthor(@PathVariable String author ){
		return bookRepository.findByAuthorContainingIgnorCase(author);
	}
	
	
	
	
	

}
