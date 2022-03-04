package org.projet.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "DVD")
@Data
public class DVD {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_DVD")
	private Long idDVD;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="COPY_NUMBER")
	private int copyNumber;
	
	@Column(name="AVAILABLE_COPIES")
	private int availableCopies;
	
	@Column(name="PUBLICATION_DATE")
	private String publicationDate;
	
	@Column(name="DIRECTOR")
	private String director;
	
	@Column(name="DURATION")
	private String duration;
	
	@Column(name= "TYPE")
	@Enumerated(EnumType.STRING)
	private TypeDVD typeDVD;
		
}
