package org.projet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.UserEntity;
import org.projet.exceptions.BookAlreadyExistsException;
import org.projet.exceptions.BookNotAvailableException;
import org.projet.exceptions.ReservationNotFoundException;
import org.projet.exceptions.UserCantBorrowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class ReservationServiceTest {

	@Autowired
	ReservationService  reservationService;

	@Autowired
	UserService userService;

	@Test
	@Transactional
	public void whenBookIsDispoAndUserCanBorrow() throws Exception {
		BookEntity book = new BookEntity();
		book.setId(1l);
		book.setIsDispo(true);
		UserEntity user = new UserEntity();
		user.setId(3l);

		ReservationBookEntity reservationBookEntity = reservationService.reserverBook(book, user);

		assertNotNull(reservationBookEntity.getId());
	}


	@Test
	@Transactional
	public void userCanBorrowButBookIsNotAvailable() throws Exception {
		BookEntity book = new BookEntity();
		book.setId(5l);
		book.setIsDispo(false);
		
		System.out.println("book is dispo ? " + book.getIsDispo());

		UserEntity user = new UserEntity();
		user.setId(4l);
		System.out.println("nb de resa d'un user " + reservationService.getNbReservation(user));

		try {
			ReservationBookEntity reservationBookEntity = reservationService.reserverBook(book, user);
		fail();
		
		} catch (Exception e) {

			String actual = e.getMessage();
			String expected = "Le livre n'est pas disponible.";

			assertEquals(expected, actual);
		}

	}
	// test user ne peut pas emprunter 

	@Test
	@Transactional
	public void userCantBorrowAndBookAvailable() throws Exception{
		BookEntity book = new BookEntity();
		book.setId(1l);
		book.setIsDispo(true);

		List <ReservationBookEntity> bookList = new ArrayList(2);

		UserEntity user = new UserEntity();
		user.setId(1l);
		user.setReservationBookEntity(bookList);

		try {
			ReservationBookEntity reservation = reservationService.reserverBook(book, user);
			fail();
		} catch(UserCantBorrowException e) {
			String actual = e.getMessage();
			String expected = "La personne ne peut plus rien réserver.";

			assertEquals(expected, actual);
		}
	}
	
	@Test
	@Transactional
	public void UserCancelReservation() {
		UserEntity user = new UserEntity();
		user.setId(1l);
		
		ReservationBookEntity reservation = new ReservationBookEntity();
		reservation.setUser(user);
		reservation.setId(1l);
		
		BookEntity book = new BookEntity();
		book.setId(1l);
		reservation.setBook(book);
		
		reservationService.cancelResaBookById(reservation.getId());
		
		assertNull(reservation);
		
		
		
	}
	

}


