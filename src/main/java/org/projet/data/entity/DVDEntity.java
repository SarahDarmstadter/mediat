package org.projet.data.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
public class DVDEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="isDispo")
	private Boolean isDispo;
	
	@Enumerated(EnumType.STRING)
	private TypeDVD typeDvd;
	
	@ManyToOne
	private DVDRefEntity reference;

	
	


}
