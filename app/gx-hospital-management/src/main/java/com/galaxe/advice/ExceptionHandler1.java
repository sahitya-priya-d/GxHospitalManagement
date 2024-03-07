package com.galaxe.advice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.galaxe.exceptions.DoctorNotFoundException;
import com.galaxe.exceptions.LocationNotFoundException;
import com.galaxe.exceptions.PatientDetailsAlreadyExistsException;
import com.galaxe.exceptions.PatientNotFoundException;
import com.galaxe.exceptions.UserAlreadyExistsException;
import com.galaxe.exceptions.UserNotFoundException;

@RestControllerAdvice
public class ExceptionHandler1 {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException ex){
		Map<String,String> errorMap=new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->{
			errorMap.put(error.getField(),error.getDefaultMessage() );
			
		});
		return errorMap;
	}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserNotFoundException.class)
	
	public Map<String,String> handleBusinessException(UserNotFoundException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PatientNotFoundException.class)
	
	public Map<String,String> handlePatientException(PatientNotFoundException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(LocationNotFoundException.class)
	
	public Map<String,String> handleLocationException(LocationNotFoundException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DoctorNotFoundException.class)
	
	public Map<String,String> handleDoctorException(DoctorNotFoundException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(UserAlreadyExistsException.class)
	
	public Map<String,String> handleuseralreadyexistsException(UserAlreadyExistsException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(PatientDetailsAlreadyExistsException.class)
	
	public Map<String,String> handlepatientDetailsalreadyexistsException(PatientDetailsAlreadyExistsException ex) {
		Map<String,String> errorMap=new HashMap<>();
		errorMap.put("error message", ex.getMessage());
	     return errorMap;
}
	

}
