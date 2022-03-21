package org.projet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class DVDNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DVDNotFoundException(String message) {
		super(message);
	}
	
	
}
