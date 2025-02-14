package com.arg.fct.service.exceptions;

public class UsuarioNotFoundException extends Exception {

	private static final long serialVersionUID = -2869013616500895087L;

	public UsuarioNotFoundException() {
	}

	public UsuarioNotFoundException(String message) {
		super(message);
	}

	public UsuarioNotFoundException(Throwable cause) {
		super(cause);
	}

	public UsuarioNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UsuarioNotFoundException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
