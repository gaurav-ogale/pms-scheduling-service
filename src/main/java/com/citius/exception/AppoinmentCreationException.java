package com.citius.exception;

@SuppressWarnings("serial")
public class AppoinmentCreationException extends RuntimeException{
	
	private final String message;
	private final String errorCode = "APP_INVALID-202";

	public AppoinmentCreationException(String message) {
		super(message);
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public String getErrorCode() {
		return errorCode;
	}

}
