package org.projet.model;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	public List<Book> findByAuthorContainingIgnorCase(String author);
	public List <Book> findByPublicationDate(Date publicationDate);
	
	
}
