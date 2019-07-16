package com.idexcel.adminservice.exception;

public class LenderAlreadyExistException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LenderAlreadyExistException() {
	}

	public LenderAlreadyExistException(String message) {
		super(message);
	}

	public LenderAlreadyExistException(Throwable cause) {
		super(cause);
	}

	public LenderAlreadyExistException(String message, Throwable cause) {
		super(message, cause);
	}

	public LenderAlreadyExistException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
