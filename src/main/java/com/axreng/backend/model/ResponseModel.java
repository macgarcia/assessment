package com.axreng.backend.model;

import java.io.Serializable;
import java.util.List;

public class ResponseModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String status;
	private List<String> urls;

	public ResponseModel(String id, String status, List<String> urls) {
		this.id = id;
		this.status = status;
		this.urls = urls;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<String> getUrls() {
		return urls;
	}

	public void setUrls(List<String> urls) {
		this.urls = urls;
	}

}
