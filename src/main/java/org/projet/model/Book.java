package org.projet.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity
@Table(name="Book")
@Data
public class Book {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_BOOK")
	private Long idBook;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="COPY_NUMBER")
	private int copyNumber;
	
	@Column(name="AVAILABLE_COPIES")
	private int availableCopies;
	
	@Column(name="PUBLICATION_DATE")
	private String publicationDate;
	
	@Column(name="AUTHOR")
	private String author;
	
	@Column(name="ISBN_NUMBER")
	private int ISBNNumber;
	
	 
	
	
}
