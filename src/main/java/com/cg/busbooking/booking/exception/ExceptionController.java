package com.cg.busbooking.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(BookingIdNotFound.class)
	public ResponseEntity<ErrorMessage> handleExceptionById(BookingIdNotFound e) {
		ErrorMessage error = new ErrorMessage();
		error.setStatusCode(HttpStatus.BAD_GATEWAY.value());
		error.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}

	@ExceptionHandler(BookingNameNotFound.class)
	public ResponseEntity<ErrorMessage> handleExceptionByName(BookingNameNotFound e) {
		ErrorMessage error = new ErrorMessage();
		error.setStatusCode(HttpStatus.BAD_GATEWAY.value());
		error.setErrorMessage(e.getMessage());
		return new ResponseEntity<>(error, HttpStatus.OK);
	}
}
