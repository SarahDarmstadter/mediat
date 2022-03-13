package org.projet.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
	
}
