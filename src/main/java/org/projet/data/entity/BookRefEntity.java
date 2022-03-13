package org.projet.data.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@Entity
@JsonInclude(JsonInclude.Include.ALWAYS)

public class BookRefEntity  {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name="title_book")
    private String title;
	
	@Column(name="author_book")
	String author;
    
	@Column(name="pubDate_book")
	private LocalDateTime publicationDate;
    
	@Column(name="isbn_book")
	private Integer isbnNumber;
	
	@OneToMany
	private List <BookEntity> bookEntity;
    

}
