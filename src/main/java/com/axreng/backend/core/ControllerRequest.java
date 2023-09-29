package com.axreng.backend.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.axreng.backend.model.ResponseModel;
import com.axreng.backend.model.Status;
import com.google.gson.Gson;

public class ControllerRequest {
	
	private Map<String, ResponseModel> requests = Collections.synchronizedMap(new HashMap<String, ResponseModel>());
	
	public void saveRequest(String id) {
		ResponseModel responseModel = new ResponseModel(id, Status.ACTIVE.getValue(), new ArrayList<String>());
		requests.put(id, responseModel);
	}
	
	public void updateRequest(String id, String url) {
		requests.get(id).getUrls().add(url);
	}
	
	public void finishRequest(String id) {
		requests.get(id).setStatus(Status.DONE.getValue());
	}
	
	public String get(String id) {
		ResponseModel responseModel = requests.get(id);
		return new Gson().toJson(responseModel);
		
	}

}
