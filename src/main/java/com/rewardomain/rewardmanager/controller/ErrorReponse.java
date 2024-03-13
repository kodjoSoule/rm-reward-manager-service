package com.rewardomain.rewardmanager.controller;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorReponse {

	@JsonProperty("status_code") 
	private Long statusCode;
	@JsonProperty("error_message")
	private String message;
	public ErrorReponse(long l, String message2) {
		// TODO Auto-generated constructor stub
		this.message = message2;
		this.statusCode = l;
		
	}
	public Long getStatusCode() {
		return statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setStatusCode(Long statusCode) {
		this.statusCode = statusCode;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
}

