package com.axreng.backend.core;

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
		htmlProcessor.findDataByTerm(key, controllerRequest);
	}

}
