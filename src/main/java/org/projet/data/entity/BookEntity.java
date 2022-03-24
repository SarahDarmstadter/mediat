package org.projet.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Data
@Entity
public class BookEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="isDispo")
	private Boolean isDispo;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	 @JoinColumn(name = "reference_id", nullable = false)
	private BookRefEntity reference;

	

	
}
