package org.projet.data.DTO;


import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;

import org.projet.data.entity.TypeDVD;

import lombok.Data;

@Data
public class DvDDTO implements Serializable {

	@NotNull
	private String title;
	
	@NotNull
	private String director;
    
	@NotNull
	private LocalDateTime publicationDate;

	@NotNull
	private TypeDVD  typeDvd;
	
	@NotNull
    private Integer duration;
	
	@NotNull
	private Integer copies;

	public String getDirector() {
		// TODO Auto-generated method stub
		return null;
	}

	public TypeDVD getTypeDvd() {
		// TODO Auto-generated method stub
		return null;
	}
	

	

}
