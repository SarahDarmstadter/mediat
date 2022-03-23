//package org.projet.controller;
//
//
//import java.util.List;
//import java.util.Optional;
//
//import org.projet.data.DTO.BookDTO;
//import org.projet.data.DTO.UserDTO;
//import org.projet.data.entity.BookEntity;
//import org.projet.data.entity.BookRefEntity;
//import org.projet.data.entity.ReservationBookEntity;
//import org.projet.data.entity.UserEntity;
//import org.projet.data.repository.BookEntityRepository;
//import org.projet.data.repository.BookRefEntityRepository;
//import org.projet.exceptions.BookAlreadyExistsException;
//import org.projet.exceptions.BookNotFoundException;
//import org.projet.exceptions.UserAlreadyExistsException;
//import org.projet.exceptions.UserNotFoundException;
//import org.projet.service.BookService;
//import org.projet.service.ReservationService;
//import org.projet.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/reservation")
//public class ReservationRestController {
//
//
//	@Autowired
//	ReservationService reservationService;
//
//	@Autowired
//	UserService userService;
//
//
//
//	
//
//	@PostMapping("/bookbooking")
//	public ResponseEntity<ReservationBookEntity> reserverLivre(@RequestBody BookEntity book,   ){
//		reservationService.reserverBook(null, null)
//	}
//
//
//}
