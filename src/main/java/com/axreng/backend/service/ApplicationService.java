package com.axreng.backend.service;

import java.util.Map;

import com.axreng.backend.core.ControllerRequest;
import com.axreng.backend.core.GeneratorId;
import com.axreng.backend.core.Processing;
import com.axreng.backend.model.RequestModel;
import com.google.gson.Gson;

public class ApplicationService {
	
	private GeneratorId generatorId;
	private ControllerRequest controllerRequest;
	
	private Processing processing;
	
	public ApplicationService() {
		this.generatorId = new GeneratorId();
		this.controllerRequest = new ControllerRequest();
	}
	
	public String startLooking(String body) {
		String id = generatorId.getId();
		RequestModel requestModel = new Gson().fromJson(body, RequestModel.class);
		controllerRequest.saveRequest(id);
		processing = new Processing(id, requestModel.getKeyword(), controllerRequest);
		processing.start();
		return new Gson().toJson(Map.of("id", id));
	}
	
	public String get(String id) {
		return controllerRequest.get(id);
	}

}
