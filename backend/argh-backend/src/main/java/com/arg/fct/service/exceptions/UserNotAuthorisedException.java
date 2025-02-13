package com.arg.fct.service.exceptions;

public class UserNotAuthorisedException extends Exception {

	private static final long serialVersionUID = 362104217449629150L;

	public UserNotAuthorisedException() {
	}

	public UserNotAuthorisedException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UserNotAuthorisedException(Throwable cause) {
		super(cause);
	}

	public UserNotAuthorisedException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotAuthorisedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
