package com.axreng.backend.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.axreng.backend.model.ResponseModel;
import com.axreng.backend.model.Status;
import com.google.gson.Gson;

public class ControllerRequest {
	
	private Map<String, ResponseModel> requests = new HashMap<String, ResponseModel>();
	
	public void saveRequest(String id) {
		ResponseModel responseModel = new ResponseModel(id, Status.ACTIVE.getValue(), new ArrayList<String>());
		requests.put(id, responseModel);
	}
	
	public synchronized void updateRequest(String id, List<String> urls) {
		requests.get(id).setStatus(Status.DONE.getValue());
		requests.get(id).setUrls(urls);
	}
	
	public String get(String id) {
		ResponseModel responseModel = requests.get(id);
		return new Gson().toJson(responseModel);
		
	}

}
