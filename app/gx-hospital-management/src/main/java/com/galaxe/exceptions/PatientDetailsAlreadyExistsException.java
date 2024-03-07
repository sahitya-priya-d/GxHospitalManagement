package com.galaxe.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PatientDetailsAlreadyExistsException extends RuntimeException{
	public PatientDetailsAlreadyExistsException(String message) {
		super(message);
		
	}
	
}
