package org.projet.data.entity;

import javax.persistence.Entity;
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
	
	private boolean isDispo;
	
	private TypeDVD typeDvd;
	
	@ManyToOne
	private DVDRefEntity reference;

	public boolean getIsDispo() {
		// TODO Auto-generated method stub
		return this.isDispo;
	}



}
