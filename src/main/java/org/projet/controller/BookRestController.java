package org.projet.controller;


import java.util.List;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.projet.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {


	@Autowired
	BookService bookService;

	@GetMapping("/allbooks")
	public List <BookRefEntity> findAllBooks(){
		return bookService.getAllRef();
	}

	@GetMapping("/{author}")
	public List <BookRefEntity> findByAuthor(@RequestParam String author ){
		return bookService.getBookRefByAuthor(author);
	}

	@GetMapping("/{id}")
	public BookEntity getBookById(@PathVariable Long id ) {
			return bookService.getBookById(id);
	}
	
	@GetMapping("/disponibles")
	public List <BookEntity> getAllBookDisponibles(){
		return bookService.getAllBookDispo();
	}
	
//	@GetMapping("/{author}/disponibles")
//	public List <BookEntity> findBookByAuthorAndDispo(@PathVariable String author){
//		return bookService.getBookByAuthorAndIsDispo(author, true);
//		
		
//	}
	
}
