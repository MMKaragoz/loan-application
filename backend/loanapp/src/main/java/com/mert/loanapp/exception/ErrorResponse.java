package com.mert.loanapp.exception;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
	
	private Date timestamp;
	
	private HttpStatus status;

	private List<CustomFieldError> errors;
	
	private String message;

	public ErrorResponse(Date timestamp, HttpStatus status, List<CustomFieldError> errors) {
		this.timestamp = timestamp;
		this.status = status;
		this.errors = errors;
	}
	
	public ErrorResponse(Date timestamp, HttpStatus status, String message) {
		this.timestamp = timestamp;
		this.status = status;
		this.message = message;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public List<CustomFieldError> getErrors() {
		return errors;
	}

	public void setErrors(List<CustomFieldError> errors) {
		this.errors = errors;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public int hashCode() {
		return Objects.hash(errors, message, status, timestamp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorResponse other = (ErrorResponse) obj;
		return Objects.equals(errors, other.errors) && Objects.equals(message, other.message) && status == other.status
				&& Objects.equals(timestamp, other.timestamp);
	}

	@Override
	public String toString() {
		return "ErrorResponse [timestamp=" + timestamp + ", status=" + status + ", errors=" + errors + ", message="
				+ message + "]";
	}


	
}
