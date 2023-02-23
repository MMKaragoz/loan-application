package com.mert.loanapp.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
		String field = "field";
		List<CustomFieldError> errors = List.of(new CustomFieldError(field, "already exists"));
       
        ErrorResponse response = new ErrorResponse(
                new Date(),
                HttpStatus.CONFLICT,
                errors
         );
         
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
	    // Get the list of validation errors from the exception
	    List<CustomFieldError> errors = exception.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> {
	            	CustomFieldError fieldError = new CustomFieldError();
	            	fieldError.setField(((FieldError) error).getField());
	            	fieldError.setMessage(error.getDefaultMessage());
	            	return fieldError;
	            })
	            .collect(Collectors.toList());
	    // Create an error response with the validation errors
	    ErrorResponse response = new ErrorResponse(
	            new Date(),
	            HttpStatus.BAD_REQUEST,
	             errors);

	    // Return the error response with a bad request status code
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
