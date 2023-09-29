package com.axreng.backend.core;

import java.util.List;

public class Processing extends Thread {
	
	private ControllerRequest controllerRequest;
	private HtmlProcessor htmlProcessor;
	private String key;
	
	public Processing(String id, String key, ControllerRequest controllerRequest, HtmlProcessor htmlProcessor) {
		super(id);
		this.key = key;
		this.controllerRequest = controllerRequest;
		this.htmlProcessor = htmlProcessor;
	}
	
	@Override
	public void run() {
		//System.out.println("Process -> " + getName());
		final List<String> urls = htmlProcessor.findDataByTerm(key);
		this.controllerRequest.updateRequest(getName(), urls);
	}

}
