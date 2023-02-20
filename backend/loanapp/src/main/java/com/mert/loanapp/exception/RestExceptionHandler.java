package com.mert.loanapp.exception;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        String errorMessage = "Error: " + exception.getMessage();
        ErrorResponse response = new ErrorResponse(
                new Date(),
                HttpStatus.BAD_REQUEST,
                errorMessage
         );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(MethodArgumentNotValidException exception, WebRequest request) {
	    // Get the list of validation errors from the exception
	    List<String> errors = exception.getBindingResult()
	            .getFieldErrors()
	            .stream()
	            .map(error -> error.getDefaultMessage())
	            .collect(Collectors.toList());
	    // Create an error response with the validation errors
	    ErrorResponse response = new ErrorResponse(
	            new Date(),
	            HttpStatus.BAD_REQUEST,
	            "Validation failed because of " + errors);

	    // Return the error response with a bad request status code
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}
}
