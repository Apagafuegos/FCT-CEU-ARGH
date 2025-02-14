package com.arg.fct.model.enums;

public enum Evaluacion {
	MARZO("Marzo"), SEPTIEMBRE("Septiembre");

	private final String displayName;

	private Evaluacion(String displayName) {
		this.displayName = displayName;
	}

	public String getDisplayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}
}
