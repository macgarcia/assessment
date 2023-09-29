package com.axreng.backend.model;

import java.io.Serializable;

public class RequestModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private String keyword;

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

}
