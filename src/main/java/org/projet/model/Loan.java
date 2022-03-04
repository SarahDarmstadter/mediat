package org.projet.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Data
@Table(name="LOANS")
public class Loan {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_LOAN")
	private Long idLoan;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LOAN_DATE")
	private Date loanDate;
	
	@ManyToOne @JoinColumn(name="idUser", nullable = false)
	private User user;

	
	
	
}
