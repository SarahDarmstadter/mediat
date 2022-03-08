package org.projet.model;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import lombok.Data;
import lombok.Getter;

@Data
public class UserRequest implements Serializable {

	// Cette classe représente les données de l'utilisateur lorsqu'il rempli le formulaire de creation de compte. 

	//Les champs firstname et lastname seront rendus obligatoires en Front
	private String firstname;
	private String lastname;
	
	@NotEmpty(message="Champs obligatoire")
	@Pattern(regexp="/^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$/g")
	private String email;
	
	@NotEmpty(message="Champs obligatoire")
	@Pattern(regexp ="/^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[-+!*$@%_])([-+!*$@%_\\w]{8,15})$/gm;\r\n" + "")
	private String password;

}
