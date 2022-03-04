package org.projet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "CD")
@Data
public class CD {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_CD")
	private Long idCD;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="COPY_NUMBER")
	private int copyNumber;
	
	@Column(name="AVAILABLE_COPIES")
	private int availableCopies;
	
	@Column(name="PUBLICATION_DATE")
	private String publicationDate;
	
	@Column(name="ARTIST")
	private String artist;
	
	@Column(name="DURATION")
	private String duration;
	
	@Column(name= "NUMBER_OF_SONGS")
	private int numberOfSongs;
		
}
