package com.arg.fct.model.enums;

public enum Ciclo {
	DAM("DAM"), DAW("DAW"), ASIR("ASIR");

	private final String displayName;

	private Ciclo(String displayName) {
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
