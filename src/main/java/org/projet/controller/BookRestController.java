package org.projet.controller;


import java.util.List;
import java.util.Optional;

import org.projet.data.DTO.BookDTO;
import org.projet.data.DTO.UserDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.UserEntity;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.projet.exceptions.BookAlreadyExistsException;
import org.projet.exceptions.BookNotFoundException;
import org.projet.exceptions.ReservationNotFoundException;
import org.projet.exceptions.UserAlreadyExistsException;
import org.projet.exceptions.UserNotFoundException;
import org.projet.service.BookService;
import org.projet.service.ReservationService;
import org.projet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRestController {


	@Autowired
	BookService bookService;
	
	@Autowired 
	UserService userService;
	
	@Autowired
	ReservationService reservationService;

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
	
	@GetMapping("/{author}/disponibles")
	public List <BookEntity> findBookByAuthorAndDispo(@PathVariable String author){
		return bookService.getBookByAuthorAndIsDispo(author, true);				
	}
	
	@GetMapping("/{id}/ref")
	public List <BookEntity> findBooksByRef(@PathVariable Long id){
		Optional<BookRefEntity> bookRef = bookService.getRefById(id);
		 return bookService.getAllBookByRef(bookRef);
	}
	
	@PostMapping("/{idBook}/{idUser}/reserver")
	public ResponseEntity <ReservationBookEntity> reserverBook(@PathVariable Long idBook, @PathVariable Long idUser ) throws Exception{
		UserEntity user = userService.getById(idUser);
		BookEntity book = bookService.getBookById(idBook);
		ReservationBookEntity resaBook = reservationService.reserverBook(book, user);
		return new ResponseEntity<ReservationBookEntity>(resaBook, HttpStatus.CREATED);
	}
	
	@PostMapping("/addBooks")
	public ResponseEntity <BookEntity> addBook(@RequestBody BookDTO bookDTO) throws BookAlreadyExistsException {
		
		if(bookService.checkIfBookRefExists(bookDTO.getTitle())) {
			throw new BookAlreadyExistsException("bookRef déjà en base");
		} else {
			List<BookEntity> newBooks = bookService.createBook(bookDTO);
			System.out.println("les livres sont ajoutés en base");
			return new ResponseEntity<BookEntity>(HttpStatus.CREATED);
		}
	}
	
	@DeleteMapping("/{id}/delete")
	public ResponseEntity <Void> deleteBook(@PathVariable long id) throws BookNotFoundException{
		bookService.deleteBookById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	@DeleteMapping("/{idReservation}/cancelReservation")
	public ResponseEntity <Void> deleteResaBook(@PathVariable Long idReservation) throws ReservationNotFoundException{
		reservationService.cancelResaBookById(idReservation);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
	
	
	
}
