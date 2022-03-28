
package org.projet.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.projet.data.DTO.BookDTO;
import org.projet.data.entity.BookEntity;
import org.projet.data.entity.BookRefEntity;
import org.projet.data.repository.BookEntityRepository;
import org.projet.data.repository.BookRefEntityRepository;
import org.projet.exceptions.BookAlreadyExistsException;
import org.projet.exceptions.BookNotFoundException;
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

	public List <BookEntity> getAllBookEntity(){
		return bookEntityRepository.findAll();
	}


	//Retrouver tous les livres disponibles par reference 
	public List <BookEntity> getAllBookDispoByRef(BookRefEntity reference){
		return bookEntityRepository.findAllByisDispoAndReference(true, reference);
	}

	public List <BookEntity> getAllBookByRef(Optional<BookRefEntity> bookRef){
		return bookEntityRepository.findAllByReference(bookRef);
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
	public boolean bookIsDispo(BookEntity bookEntity) throws BookNotFoundException{
		//verifions qu'il existe en bdd
		BookEntity book = bookEntityRepository.findById(bookEntity.getId())
				.orElseThrow(() -> new BookNotFoundException("Le livre n'existe pas."));
		return book.getIsDispo();	

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

	public List <BookRefEntity> getBookRefbyId(Long id) {
		return bookRefRepository.findAllById(id);
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


	public List<BookRefEntity> getRefByAuthorAndIsDispo(String author, boolean b) {
		List <BookRefEntity> listRef = bookRefRepository.findByAuthorIgnoreCase(author);
		List <BookEntity> listBookDispo = new ArrayList<>();

		//creation d'une liste de ref dont au moins 1 entity est dispo 
		List <BookRefEntity> listRefDispo = new ArrayList<>();


		//check sil y a des livres dispo dans la ref. 
		for (BookRefEntity bookRefEntity : listRef) {
			List <BookEntity> bookDispo =  getAllBookDispoByRef(bookRefEntity);
			if (bookDispo.size() >=1) {
				listRefDispo.add(bookRefEntity);
			}
		} return listRefDispo;
	}
	
	//Enregistrer des livres 
	public List<BookEntity> createBook(BookDTO bookDTO) throws BookAlreadyExistsException {

		if(bookRefRepository.findByTitle(bookDTO.getTitle()) !=null) {
			throw new BookAlreadyExistsException("Le livre est déjà en base");
		} else {
			BookRefEntity bookRefEntity = new BookRefEntity();
			bookRefEntity.setAuthor(bookDTO.getAuthor());
			bookRefEntity.setIsbnNumber(bookDTO.getIsbnNumber());
			bookRefEntity.setPublicationDate(bookDTO.getPublicationDate());
			bookRefEntity.setTitle(bookDTO.getTitle());
			bookRefEntity.setCopies(bookDTO.getCopies());
			bookRefEntity = bookRefRepository.save(bookRefEntity);
			List <BookEntity> bookList = new ArrayList<BookEntity>();

			for (int i = 0; i < bookDTO.getCopies(); i++) {

				BookEntity bookEntity = new BookEntity();
				bookEntity.setIsDispo(true);
				bookEntity.setReference(bookRefEntity);
				bookEntityRepository.save(bookEntity);	
				bookList.add(bookEntity);

			} bookRefEntity.setBookEntity(bookList);	
			return bookList;
		}	

	}

	public boolean checkIfBookRefExists(String title) {
		if(bookRefRepository.findByTitle(title) == null) {
			return false;
		}
		return true;
	}

	// supprimer un livre en particulier sans supprimer la réference
	public void deleteBookById(Long id) throws BookNotFoundException{
		BookEntity book = bookEntityRepository.getById(id);
		if(book == null) {
			throw new BookNotFoundException("Le livre ayant pour id " + id +" n'existe pas");
		} else {
			bookEntityRepository.delete(book);
			BookRefEntity bookRef = bookRefRepository.getById(book.getId());
			bookRef.setCopies(bookRef.getCopies()-1);	
			updateBookRef(bookRef);
		}
	}

	// mise à jour du bookRefEntity 

	public BookRefEntity updateBookRef(BookRefEntity bookRefUpdate) throws BookNotFoundException {
		if (bookRefRepository.getById(bookRefUpdate.getId()) == null) {
			throw new BookNotFoundException("Le livre n'existe pas.");
		} else {
			BookRefEntity bookRefEntity = bookRefRepository.getById(bookRefUpdate.getId());
			bookRefEntity.setAuthor(bookRefUpdate.getAuthor());
			bookRefEntity.setCopies(bookRefUpdate.getCopies());
			bookRefEntity.setIsbnNumber(bookRefUpdate.getIsbnNumber());
			bookRefEntity.setPublicationDate(bookRefUpdate.getPublicationDate());
			bookRefEntity.setTitle(bookRefUpdate.getTitle());
			bookRefEntity.setBookEntity(bookRefUpdate.getBookEntity());
			return bookRefRepository.save(bookRefEntity);
		}
	}

	public Optional<BookRefEntity> getRefById(Long id) {
		return bookRefRepository.findById(id);
	}

	public List<BookRefEntity> getBookByTitle(String title) {
		return bookRefRepository.findAllByTitle(title);
	}






}
