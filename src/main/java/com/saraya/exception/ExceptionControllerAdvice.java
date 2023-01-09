package com.saraya.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

	@ExceptionHandler(WeCareException.class)
	public ResponseEntity<ErrorMessage> ExceptionHandler1(WeCareException ex){
		ErrorMessage error = new ErrorMessage();
		
		error.setMessage(ex.getMessage());
		return ResponseEntity.ok(error);  
		  
		
	}

	@ExceptionHandler(Exception.class)
	public String  ExceptionHandler2(Exception ex) {
		return ex.getMessage();
	}
}
