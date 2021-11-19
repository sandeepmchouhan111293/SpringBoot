package com.example.demo.entity;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class ErrorMessage {
	
	private HttpStatus status;
	private String message;
		
	public ErrorMessage(HttpStatus notFound, String message1) {
		status = notFound;
		message =message1;
	}

	public ErrorMessage() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "ErrorMessage [status=" + status + ", message=" + message + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(message, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ErrorMessage other = (ErrorMessage) obj;
		return Objects.equals(message, other.message) && status == other.status;
	}
	
	
}
