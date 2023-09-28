package com.axreng.backend.core;

import java.util.Arrays;
import java.util.List;

public class Processing extends Thread {
	
	private ControllerRequest controllerRequest;
	private String key;
	
	public Processing(String id, String key, ControllerRequest controllerRequest) {
		super(id);
		this.key = key;
		this.controllerRequest = controllerRequest;
	}
	
	@Override
	public void run() {
		try {
			String getenv = System.getenv("BASE_URL");
			System.out.println("Thread -> " + getName());
			System.out.println(getenv);
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		List<String> urls = Arrays.asList("teste 01", "teste02");
		this.controllerRequest.updateRequest(getName(), urls);
	}

}
