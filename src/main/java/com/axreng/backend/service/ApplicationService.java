package com.axreng.backend.service;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.axreng.backend.core.ControllerRequest;
import com.axreng.backend.core.GeneratorId;
import com.axreng.backend.core.HtmlProcessor;
import com.axreng.backend.core.Processing;
import com.axreng.backend.model.RequestModel;
import com.google.gson.Gson;

public class ApplicationService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 32;

	private GeneratorId generatorId;
	private ControllerRequest controllerRequest;
	private HtmlProcessor htmlProcessor;

	private Processing processing;

	public ApplicationService() {
		this.generatorId = new GeneratorId();
		this.controllerRequest = new ControllerRequest();
		this.htmlProcessor = new HtmlProcessor();
	}

	public String startLooking(String body) {

		RequestModel requestModel = new Gson().fromJson(body, RequestModel.class);
		validation(requestModel.getKeyword());

		String id = generatorId.getId();

		controllerRequest.saveRequest(id);
		
		processing = new Processing(id, requestModel.getKeyword(), controllerRequest, htmlProcessor);
		
		final String log = String.format("Start of process with id: %s, keyword: %s", id, requestModel.getKeyword());
		
		LOGGER.info(log);
		
		processing.start();

		return new Gson().toJson(Map.of("id", id));
	}

	private void validation(String keyword) {
		if (keyword.length() < MIN_LENGTH || keyword.length() > MAX_LENGTH) {
			LOGGER.error("Invalid keyword: " + keyword);
			throw new IllegalArgumentException("Invalid keyword: " + keyword);
		}
	}

	public String get(String id) {
		return controllerRequest.get(id);
	}

}
