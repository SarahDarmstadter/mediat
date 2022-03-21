
package org.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.projet.data.DTO.BookDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.entity.UserEntity;
import org.projet.data.entity.UserRole;
import org.projet.data.entity.UserStatus;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class BookService {

	@Autowired
	BookEntityRepository bookEntityRepository;

	@Autowired
	BookRefEntityRepository bookRefRepository; 

	// Retrouver tous les livres disponibles 
	public List<BookEntity> getAllBookDispo(){
		return bookEntityRepository.findAllByisDispo(true);
	}

	//Retrouver tous les livres disponibles par reference 
	public List <BookEntity> getAllBookDispoByRef(BookRefEntity reference){
		return bookEntityRepository.findAllByisDispoAndReference(true, reference);
	}

	//Compter le nombre de livres disponibles d'une même référence 
	public Integer getLivreDispoCountByRef(BookRefEntity bookReference) {
		return bookEntityRepository.countAllByReference(bookReference);
	}

	// Retrouver tous les livres d'un auteur
	public List <BookRefEntity> getBookRefByAuthor(String author) {
		return bookRefRepository.findByAuthorIgnoreCase(author);
	}

	//Verifier la disponibilité d'un livre
	public boolean bookIsDispo(BookEntity bookEntity) throws NoSuchElementException{
		//verifions qu'il existe en bdd
		BookEntity book = bookEntityRepository.findById(bookEntity.getId())
				.orElseThrow(() -> new NoSuchElementException("Le livre " + bookEntity.getId() + " n'existe pas."));
		return bookEntity.getIsDispo();	

	}

	//Updater un livre (dispo / plus dispo)
	public BookEntity updateBook(BookEntity bookEntity) {
		if (!bookEntityRepository.existsById(bookEntity.getId())) {
			throw new NoSuchElementException("Le livre " + bookEntity.getId() + " n'existe pas.");
		}
		return bookEntityRepository.save(bookEntity);
	}

	public List<BookRefEntity> getAllRef() {
		return bookRefRepository.findAll();
	}

	public BookEntity getBookById(Long id) {
		return bookEntityRepository.getById(id);
	}

	public List<BookEntity> getBookByAuthorAndIsDispo(String author, boolean b) {
		List <BookRefEntity> listRef = bookRefRepository.findByAuthorIgnoreCase(author);
		List <BookEntity> listBookDispo = new ArrayList<>();
		for (BookRefEntity bookRefEntity : listRef) {
			List <BookEntity> bookDispo =  getAllBookDispoByRef(bookRefEntity);
			listBookDispo.addAll(bookDispo);
		}
		return listBookDispo;

	}

	//Enregistrer des livres 
	public List<BookEntity> createBook(BookDTO bookDTO) {
		BookRefEntity bookRefEntity = new BookRefEntity();
		bookRefEntity.setAuthor(bookDTO.getAuthor());
		bookRefEntity.setIsbnNumber(bookDTO.getIsbnNumber());
		bookRefEntity.setPublicationDate(bookDTO.getPublicationDate());
		bookRefEntity.setTitle(bookDTO.getTitle());
		bookRefEntity = bookRefRepository.save(bookRefEntity);
		List <BookEntity> bookList = new ArrayList<BookEntity>();

		for (int i = 0; i < bookDTO.getCopyNumber(); i++) {

			BookEntity bookEntity = new BookEntity();
			bookEntity.setIsDispo(true);
			bookEntity.setReference(bookRefEntity);
			bookEntityRepository.save(bookEntity);	
			bookList.add(bookEntity);

		} bookRefEntity.setBookEntity(bookList);	
			return bookList;

	}

	public boolean checkIfBookExists(String title) {
		if(bookRefRepository.findByTitle(title) == null) {
			return false;
		}
		
		return true;
	}


	


}
