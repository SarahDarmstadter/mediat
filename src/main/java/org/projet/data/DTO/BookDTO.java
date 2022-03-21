package org.projet.data.DTO;


import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO implements Serializable {

	// Cette classe représente la creation d'un livre en bdd. 

	@NotNull
	private String title;
	
	@NotNull
	private String author;
    
	@NotNull
	private LocalDateTime publicationDate;

	@NotNull
	private Integer isbnNumber;
	
	@NotNull
	private Integer copyNumber;
	

}
