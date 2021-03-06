package com.infybank.utility;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infybank.entity.ErrorInfo;
import com.infybank.exception.InfyBankException;

@RestControllerAdvice
public class InfyBankExceptionHandler{

	@Autowired
	private Environment environment;
	
	private static final long serialVersionUID = 1L;

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> genericExceptionHandler(Exception exception) {
	ErrorInfo error = new ErrorInfo();
	error.setErrorMessage(environment.getProperty("General.EXCEPTION_MESSAGE"));
	error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
	error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.INTERNAL_SERVER_ERROR);				
	}
	
	@ExceptionHandler(InfyBankException.class)
	public ResponseEntity<ErrorInfo> infyBankExceptionHandler(InfyBankException exception) {
	ErrorInfo error = new ErrorInfo();
	error.setErrorMessage(environment.getProperty(exception.getMessage()));
	error.setErrorCode(HttpStatus.NOT_FOUND.value());
	error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.NOT_FOUND);				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> argumentExceptionHandler(MethodArgumentNotValidException exception) {
	ErrorInfo error = new ErrorInfo();
	String errorMsg =exception.getBindingResult().
			getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
	error.setErrorMessage(errorMsg);
	error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);				
	}
	
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> constraintViolationExceptionHandler(ConstraintViolationException exception) {
	ErrorInfo error = new ErrorInfo();
	String errorMsg =exception.getConstraintViolations().
			stream().map(x->x.getMessage()).collect(Collectors.joining(","));
	error.setErrorMessage(errorMsg);
	error.setErrorCode(HttpStatus.BAD_REQUEST.value());
	error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);				
	}
	
	
}
