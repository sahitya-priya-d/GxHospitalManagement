package com.galaxe.gxhospitalmanagement.advice;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.galaxe.gxhospitalmanagement.exception.ExceptionResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UsernameNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	protected @ResponseBody ExceptionResponse handleUserNameNotFoundException(UsernameNotFoundException ex) {
		return new ExceptionResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());
	}
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	protected @ResponseBody ExceptionResponse handleBadCredentialsException(BadCredentialsException ex) {
		return new ExceptionResponse(HttpStatus.UNAUTHORIZED.value(), ex.getMessage());
	}
}
