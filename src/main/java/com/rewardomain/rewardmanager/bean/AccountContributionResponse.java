package com.rewardomain.rewardmanager.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccountContributionResponse {
	
	@JsonProperty("status_code")
	private int code;
	private String message;
	public AccountContributionResponse() {}

	public AccountContributionResponse(int code, String message) {
		
		this.code = code;
		this.message = message;
	}

	

	public int getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getExecutionChain() {
		return executionChain;
	}

	public void setExecutionChain(String executionChain) {
		this.executionChain = executionChain;
	}
	private String executionChain;
	public AccountContributionResponse(int code, String message, String executionChain) {
		super();
		this.code = code;
		this.message = message;
		this.executionChain = executionChain;
	}
	
	
}
