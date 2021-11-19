package com.infy.exception;

public class InfyCourierException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public InfyCourierException() {
		super();
	}
	
	public InfyCourierException(String errors) {
		super(errors);
	}
	
	

}
