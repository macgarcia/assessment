package com.axreng.backend.core;

import java.util.UUID;

public class GeneratorId {
	
	private String generator() {
		UUID uuid = UUID.randomUUID();
		String hash = uuid.toString().replace("-", "");
		return hash.substring(0, 8);
	}
	
	public String getId() {
		return generator();
	}

}
