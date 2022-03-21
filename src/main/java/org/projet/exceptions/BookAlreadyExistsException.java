package org.projet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)

public class BookAlreadyExistsException extends Exception {

	private static final long serialVersionUID = 1L;

	public BookAlreadyExistsException(String message) {
		super(message);
	}
}




