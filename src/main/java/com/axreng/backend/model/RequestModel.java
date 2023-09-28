package com.axreng.backend.model;

import java.io.Serializable;
import java.util.Objects;

public class RequestModel implements Serializable {

	private static final long serialVersionUID = 1L;

	private final int MIN_LENGTH = 4;
	private final int MAX_LENGTH = 32;

	private String keyword;

	public String getKeyword() {
		if (Objects.isNull(keyword) || keyword.length() < MIN_LENGTH || keyword.length() > MAX_LENGTH) {
			throw new IllegalArgumentException();
		}
		return keyword;
	}

	public void setKeyword(String keyword) {

		this.keyword = keyword;
	}

}
