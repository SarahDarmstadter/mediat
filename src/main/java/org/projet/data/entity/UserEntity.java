package org.projet.data.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.NotNull;


import lombok.Data;
@Entity
@Data
public class UserEntity {
	
		
		@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="ID_USER")
		private Long id;
		
		@Column(name="EMAIL", unique =true)
		@NotNull
		private String email;
		
		@Column(name="PASSWORD")
		@NotNull
		//@Pattern(regexp ="/^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$/gm;\r\n" + "")
		private String password;
		
		@NotNull
		@Column(name="lastname")
		private String lastname;
		
		@NotNull
		@Column(name="firstname")
		private String firstname;
		
		@Enumerated(EnumType.STRING)
		@Column(name="Role")
		private UserRole userRole;
		
		@Enumerated(EnumType.STRING)
		private UserStatus userStatus;
		
		@OneToMany
		private List<ReservationBookEntity> reservationBookEntity;
		
		@OneToMany
		private List <ReservationCDEntity> reservationCDEntity;
		
		@OneToMany
		private List <ReservationDVDEntity> reservationDVDEntity;

		
		
}
