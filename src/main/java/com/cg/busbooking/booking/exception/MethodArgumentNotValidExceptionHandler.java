package com.cg.busbooking.booking.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class MethodArgumentNotValidExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Error handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
		BindingResult result = ex.getBindingResult();

		List<String> errorList = new ArrayList<>();
		result.getFieldErrors()
				.forEach(fieldError -> errorList.add(fieldError.getObjectName() + "." + fieldError.getField() + " : "
						+ fieldError.getDefaultMessage() + " : rejected value [" + fieldError.getRejectedValue()
						+ "]"));
		result.getGlobalErrors().forEach(
				fieldError -> errorList.add(fieldError.getObjectName() + " : " + fieldError.getDefaultMessage()));

		return new Error(HttpStatus.BAD_REQUEST, errorList);
	}

	public static class Error {
		private int errorCode;
		private String errorName;
		private List<String> fieldErrors = new ArrayList<>();

		public Error(HttpStatus status, List<String> fieldErrors) {
			this.errorCode = status.value();
			this.errorName = status.name();
			this.fieldErrors = fieldErrors;
		}

		public int getErrorCode() {
			return errorCode;
		}

		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}

		public String getErrorName() {
			return errorName;
		}

		public void setErrorName(String errorName) {
			this.errorName = errorName;
		}

		public List<String> getFieldErrors() {
			return fieldErrors;
		}

		public void setFieldErrors(List<String> fieldErrors) {
			this.fieldErrors = fieldErrors;
		}
	}
}
