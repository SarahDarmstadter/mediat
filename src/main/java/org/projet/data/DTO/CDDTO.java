package org.projet.data.DTO;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CDDTO implements Serializable {

// Cette classe représente la creation d'un CD en bdd. 

	@NotNull
	private String title;
	
	@NotNull
	private String artist;
    
	@NotNull
	private LocalDateTime publicationDate;

	@NotNull
	private Integer songNumber;

	@NotNull
    private Integer duration;
	
	@NotNull
	private Integer copies;
	

}
