package com.axreng.backend.model;

public enum Status {
	
	ACTIVE("active"), DONE("done");
	
	private final String value;

	Status(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}

}
