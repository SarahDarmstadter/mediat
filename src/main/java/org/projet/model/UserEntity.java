package org.projet.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.Data;

@Entity
@Table(name= "USERS")
@Data
public class UserEntity {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID_USER")
	private Long idUser;
	
	@OneToMany(cascade=CascadeType.ALL,orphanRemoval=true)
	@JoinColumn(name="ID_LOAN")
	private List <Loan> userLoans;
	
	@Column(name="EMAIL", unique =true)
	@NotNull
	private String email;
	
	@Column(name="PASSWORD")
	@NotNull
	//@Pattern(regexp ="/^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$/gm;\r\n" + "")
	private String password;
	
	@Column(name="LASTNAME")
	@NotNull
	private String lastName;
	
	@Column(name="FIRST")
	@NotNull
	private String firstName;
	
	@Column(name="PENDING_LOANS")
	@Min(0)
	@Max(3)
	private int pendingLoan;
	
	@Column(name="USER_ROLE")
	@Enumerated(EnumType.STRING)
	private UserRole userRole;
	
	@Enumerated(EnumType.STRING)
	private UserStatus userStatus;

	public Long getIdUser() {
		return idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public List<Loan> getUserLoans() {
		return userLoans;
	}

	public void setUserLoans(List<Loan> userLoans) {
		this.userLoans = userLoans;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getPendingLoan() {
		return pendingLoan;
	}

	public void setPendingLoan(int pendingLoan) {
		this.pendingLoan = pendingLoan;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
	
	public UserStatus getUserStatus() {
		return userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}
	

	
}
