package com.infy.utility;

import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.infy.exception.InfyCourierException;
import com.infy.utility.ErrorInfo;

@RestControllerAdvice
public class ExceptionControllerAdvice {
	
	 private static final Log LOGGER = LogFactory.getLog(ExceptionControllerAdvice.class);


	@Autowired
	private Environment environment;
	
	ErrorInfo error = new ErrorInfo();
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setErrorMessage(environment.getProperty("GENERAL_EXCEPTION_MESSAGE"));
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(InfyCourierException.class)
	public ResponseEntity<ErrorInfo> infyCourierExceptionHandler(InfyCourierException exception) {
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setErrorMessage(environment.getProperty("Service.NO_RECORDS_FOUND"));
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
	}	
		
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorInfo> infyCourierExceptionHandler(MethodArgumentNotValidException exception) {
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String errMsg=exception.getBindingResult().getAllErrors().stream().map(x->x.getDefaultMessage()).collect(Collectors.joining(","));
		error.setErrorMessage(errMsg);
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
	}	

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<ErrorInfo> infyCourierExceptionHandler(ConstraintViolationException exception) {
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		String errMsg= exception.getConstraintViolations().stream().map(x->x.getMessage()).collect(Collectors.joining(","));
		error.setErrorMessage(errMsg);
		return new ResponseEntity<ErrorInfo>(error,HttpStatus.BAD_REQUEST);
	}

}
