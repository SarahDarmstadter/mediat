package org.projet.service;

import java.time.LocalDateTime;
import java.util.List;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.entity.CdEntity;
import org.projet.data.entity.DvdEntity;
import org.projet.data.entity.ReservationBookEntity;
import org.projet.data.entity.ReservationCdEntity;
import org.projet.data.entity.ReservationDvdEntity;
import org.projet.data.entity.UserEntity;
import org.projet.data.repository.ReservationBookEntityRepository;
import org.projet.data.repository.ReservationCDEntityRepository;
import org.projet.data.repository.ReservationDVDEntityRepository;
import org.projet.exceptions.BookNotAvailableException;
import org.projet.exceptions.UserCantBorrowException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

	@Autowired
	private ReservationBookEntityRepository reservationBookEntityRepository;

	@Autowired
	private ReservationCDEntityRepository reservationCDEntityRepository;

	@Autowired
	private ReservationDVDEntityRepository reservationDVDEntityRepository;

	@Autowired
	private BookService bookService;

	@Autowired
	private CDService cdService;

	@Autowired
	private DVDService dvdService;


	public List<ReservationBookEntity> getAllReservationBook() {
		return reservationBookEntityRepository.findAll();
	}

	public List<ReservationCdEntity> getAllReservationCD() {
		return reservationCDEntityRepository.findAll();
	}

	public List<ReservationDvdEntity> getAllReservationDVD() {
		return reservationDVDEntityRepository.findAll();
	}



	//RESERVER UN LIVRE 
	public ReservationBookEntity reserverBook(BookEntity book, UserEntity user) throws Exception {
		// Vérifier que le livre existe et qu'il est dispo
		if (!bookService.bookIsDispo(book)) {
			throw new BookNotAvailableException("Le livre n'est pas disponible.");
		}

		// Vérifier qu'il lui reste des réservations disponibles
		if (getNbReservation(user) >= 3) {
			throw new UserCantBorrowException("La personne " + user.getId() + " ne peut plus rien réserver.");
		}

		// Créer une réservation
		ReservationBookEntity reservation = new ReservationBookEntity();
		reservation.setBook(book);
		reservation.setBorrowingDate(LocalDateTime.now());
		reservation.setReturningDate(LocalDateTime.now().plusDays(7));
		reservation.setUser(user);


		// Rendre le livre indispo
		book.setIsDispo(false);
		book = bookService.updateBook(book);

		return saveReservationBook(reservation);
	}

	private ReservationBookEntity saveReservationBook(ReservationBookEntity reservation) {
		return reservationBookEntityRepository.save(reservation);
	}
	
	// Annuler une reservation de livre 
	public void cancelResaBookById(Long id) {
		ReservationBookEntity reservation = reservationBookEntityRepository.getById(id);
		BookEntity book = reservation.getBook();
		book.setIsDispo(true);
		bookService.updateBook(book);
		reservationBookEntityRepository.delete(reservation);
	}
	
	

	//RESERVER UN CD
	public ReservationCdEntity reserverCD(CdEntity cd, UserEntity user) throws Exception {
		// Vérifier que le disque existe et qu'il est dispo
		if (!cdService.cdIsDispo(cd)) {
			throw new Exception("Le disque " + cd.getId() + " n'est pas disponible.");
		}

		// Vérifier qu'il lui reste des réservations disponibles
		if (getNbReservation(user) >= 3) {
			throw new Exception("La personne " + user.getId() + " ne peut plus rien réserver.");
		}

		// Rendre le disque indispo
		cd.setIsDispo(false);
		cd = cdService.updateCd(cd);

		// Créer une réservation
		ReservationCdEntity reservation = new ReservationCdEntity();
		reservation.setCd(cd);
		reservation.setBorrowingDate(LocalDateTime.now());
		reservation.setReturningDate(LocalDateTime.now().plusDays(7));
		reservation.setUser(user);
		return saveReservationCD(reservation);
	}


	private ReservationCdEntity saveReservationCD(ReservationCdEntity reservation) {
		return reservationCDEntityRepository.save(reservation);
	}
	
	// annuler une reservation de CD
	
	public void cancelResaCDById(Long id) {
		ReservationCdEntity reservation = reservationCDEntityRepository.getById(id);
		CdEntity cd = reservation.getCd();
		cd.setIsDispo(true);
		cdService.updateCd(cd);
		reservationCDEntityRepository.delete(reservation);
	}



	//RESERVER UN DVD 
	public ReservationDvdEntity reserverDVD(DvdEntity dvd, UserEntity user) throws Exception {
		// Vérifier que le disque existe et qu'il est dispo
		if (!dvdService.dvdIsDispo(dvd)) {
			throw new Exception("Le dvd " + dvd.getId() + " n'est pas disponible.");
		}

		// Vérifier qu'il lui reste des réservations disponibles
		if (getNbReservation(user) >= 3) {
			throw new Exception("La personne " + user.getId() + " ne peut plus rien réserver.");
		}

		// Rendre le disque indispo
		dvd.setIsDispo(false);
		dvd = dvdService.updateDvd(dvd);

		// Créer une réservation
		ReservationDvdEntity reservation = new ReservationDvdEntity();
		reservation.setDvd(dvd);
		reservation.setBorrowingDate(LocalDateTime.now());
		reservation.setReturningDate(LocalDateTime.now().plusDays(7));
		reservation.setUser(user);
		return saveReservationDVD(reservation);
	}

	private ReservationDvdEntity saveReservationDVD(ReservationDvdEntity reservation) {
		return reservationDVDEntityRepository.save(reservation);
	}
	
	// annuler une reservation de DVD 
	public void cancelResaDVDById(Long id) {
		ReservationDvdEntity reservation = reservationDVDEntityRepository.getById(id);
		DvdEntity dvd = reservation.getDvd();
		dvd.setIsDispo(true);
		dvdService.updateDvd(dvd);
		reservationDVDEntityRepository.delete(reservation);
	}

	public Integer getReservationBookByUser(UserEntity user) {
		return reservationBookEntityRepository.countAllByUser(user);
	}

	public Integer getReservationCDByUser(UserEntity user) {
		return reservationCDEntityRepository.countAllByUser(user);
	}

	public Integer getReservationDVDByUser(UserEntity user) {
		return reservationDVDEntityRepository.countAllByUser(user);
	}

	public Integer getNbReservation(UserEntity user) {
		return getReservationBookByUser(user) + getReservationCDByUser(user) + getReservationDVDByUser(user);
	}





}
