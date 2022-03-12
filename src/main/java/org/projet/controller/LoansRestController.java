//package org.projet.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.projet.data.entity.Book;
//import org.projet.exceptions.BookNotFoundException;
//import org.projet.model.BookRepository;
//import org.projet.model.CDRepository;
//import org.projet.model.DVDRepository;
//import org.projet.model.Loan;
//import org.projet.model.LoanRepository;
//import org.projet.model.UserEntity;
//import org.projet.model.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/loans")
//public class LoansRestController {
//
//	@Autowired
//	LoanRepository loanRepository;
//	
//	@Autowired
//	UserRepository userRepository;
//	
//	@Autowired
//	BookRepository bookRepository;
//	
//	@Autowired
//	CDRepository cdRepository;
//	
//	@Autowired
//	DVDRepository dvdRepository;
//	
//	
//	
//	@PostMapping("/bookReservation")
//	public ResponseEntity <Loan> borrowBook(@RequestParam String title) throws BookNotFoundException{
//	List<Book> sameBooks = bookRepository.findAllByTitle(title);
//		if (sameBooks == null) {
//			throw new BookNotFoundException("Nous n'avons pas ce livre à la médiatheque");
//		} else {
//			Object[] arrayOfBooks = sameBooks.toArray();
//			for (int i = 0; i <= arrayOfBooks.length; i++) {
//				System.out.println(arrayOfBooks[i]);
//				
//			}
//			Loan loan = new Loan();
//			return new ResponseEntity<Loan>(loan, HttpStatus.CREATED);
//
//		}
//		
//	}
//	
//	
//}
