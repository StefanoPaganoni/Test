package com.lastminute.exception;

public class ValidationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 548052256166476476L;
	
	static final String errorPrefix = "[WrapperValidationException] - ";

	
	public ValidationException() {
	}

	public ValidationException(String message) {
		super(errorPrefix+message,null);
	}

	public ValidationException(Throwable cause) {
		super(errorPrefix+cause);
	}

	public ValidationException(String message, Throwable cause) {
		super(errorPrefix+message, cause);
	}

	public ValidationException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(errorPrefix+message, cause, enableSuppression, writableStackTrace);
	}

}
