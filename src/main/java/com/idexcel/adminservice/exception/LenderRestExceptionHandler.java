package com.idexcel.adminservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class LenderRestExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<LenderErrorResponse> handleException(LenderNotFoundException exc) {
		
		LenderErrorResponse error = new LenderErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
	
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<LenderErrorResponse> handleException(Exception exc) {
		
		// create CustomerErrorResponse
		
		LenderErrorResponse error = new LenderErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		
		// return ResponseEntity
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
