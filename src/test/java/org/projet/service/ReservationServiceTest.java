package org.projet.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.UserEntity;
import org.projet.exceptions.BookAlreadyExistsException;
import org.projet.exceptions.BookNotAvailableException;
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

		UserEntity user = new UserEntity();
		user.setId(4l);

		try {
			ReservationBookEntity reservationBookEntity = reservationService.reserverBook(book, user);
		} catch (BookNotAvailableException e) {

			String actual = e.getMessage();
			String expected = "Le livre n'est pas disponible.";

			assertEquals(expected, actual);
		}




	}
}

