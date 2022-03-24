package org.projet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.projet.data.DTO.BookDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.exceptions.BookAlreadyExistsException;
import org.projet.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class BookServiceTest {

	@Autowired
	BookService bookService;

	@Test
	public void addNewBookNotAlreadyInDataBase() throws BookAlreadyExistsException {
		BookDTO newbook = new BookDTO();
		newbook.setCopies(2);
		newbook.setAuthor("Ecrivain Test");
		newbook.setIsbnNumber(675438);
		newbook.setTitle("Premiere Classe de test");

		List <BookEntity> newBooks = bookService.createBook(newbook);

		for (BookEntity newBook : newBooks) {
			assertNotNull(newBook.getId());
		}
	}


	@Test
	public void addBookAlreadyInBase(){

		BookRefEntity bookInBase = new BookRefEntity();
		bookInBase.setCopies(2);
		bookInBase.setAuthor("Auteur UN");
		bookInBase.setIsbnNumber(1234567);
		bookInBase.setTitle("Le Crimier apprend à coder");

		BookDTO newbook = new BookDTO();
		newbook.setCopies(2);
		newbook.setAuthor("Auteur UN");
		newbook.setIsbnNumber(1234567);
		newbook.setTitle("Le Crimier apprend à coder");

		try {
			List <BookEntity> newBooks = bookService.createBook(newbook);
		} catch (BookAlreadyExistsException e) {

			String actual = e.getMessage();
			String expected = "Le livre est déjà en base";

			assertEquals(expected, actual);
		}
	}

	@Test 
	@Transactional
	public void updateBookRef() throws BookNotFoundException {	
			try {
				
				List <BookEntity> bookList = new ArrayList();
				
				BookRefEntity bookupdate = new BookRefEntity();
				bookupdate.setId(1l);
				bookupdate.setAuthor("Auteur Deux");
				bookupdate.setCopies(3);
				bookupdate.setIsbnNumber(1234567);
				bookupdate.setBookEntity(bookList);
				bookupdate.setTitle("Le Crimier apprend à coder");
				
				BookRefEntity bookInBase = new BookRefEntity();
				bookInBase.setId(1l);
				bookInBase.setAuthor(bookupdate.getAuthor());
				bookInBase.setIsbnNumber(bookupdate.getIsbnNumber());
				bookInBase.setTitle(bookupdate.getTitle());
				bookInBase.setBookEntity(bookupdate.getBookEntity());
				bookInBase.setCopies(bookupdate.getCopies());
				
				bookService.updateBookRef(bookInBase);
				assertEquals(bookInBase, bookupdate);
			} catch(BookNotFoundException e) {
				String actual = e.getMessage();
				String expected = "Le livre n'existe pas";

				assertEquals(expected, actual);
			}
			
		
	}

}



