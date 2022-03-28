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

	
	//Pour les references de livre 
	
	@GetMapping()
	public List <BookRefEntity> findAllBooks(@RequestParam(required = false) String author, @RequestParam(required=false) Long id, @RequestParam(required = false) boolean disponible ){
		if(author == null && id !=null ) {
			return (List<BookRefEntity>) bookService.getBookRefbyId(id);

		} else if (id == null && author!=null ) {
			return bookService.getBookRefByAuthor(author);
 
		} else if (id==null && author != null && disponible==true) { 
			return bookService.getRefByAuthorAndIsDispo(author, disponible);				
		}else {
			return bookService.getAllRef();
		}
		
	}
	
	//Pour les entités de livre 
	
	@GetMapping("/entity")
	public List <BookEntity> getAllBookEntities(@RequestParam(required = false) boolean disponible) {
		
		if( !disponible ) {
			return bookService.getAllBookEntity();
		} else {
			return bookService.getAllBookDispo();
		}
	}
	
	
//	@GetMapping("/{author}?/")
//	public List <BookRefEntity> findByAuthor(@RequestParam String author ){
//		return bookService.getBookRefByAuthor(author);
//	}
//
////	@GetMapping("/{id}?")
////	public BookRefEntity getBookById(@PathVariable Long id ) {
////			return bookService.getBookRefbyId(id);
////	}
	
	
//	
//	@GetMapping("/{author}/disponibles")
//	public List <BookEntity> findBookByAuthorAndDispo(@PathVariable String author){
//		return bookService.getBookByAuthorAndIsDispo(author, true);				
//	}
		
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
