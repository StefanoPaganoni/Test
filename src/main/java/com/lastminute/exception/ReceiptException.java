package com.lastminute.exception;

public class ReceiptException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 548052256166476476L;

	
	public ReceiptException() {
	}

	public ReceiptException(String message) {
		super(message,null);
	}

	public ReceiptException(Throwable cause) {
		super(cause);
	}

	public ReceiptException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReceiptException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
