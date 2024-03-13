package com.rewardomain.rewardmanager.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Confirmation {
	

	@JsonProperty("reward_confirmation_number")
	private long rewardConfirmation;
	@JsonProperty("execution_chain")
	private String executionChain;
	public Confirmation(long confirmationNumber) {
		// TODO Auto-generated constructor stub
		this.rewardConfirmation = confirmationNumber;
	}
	
	public Confirmation(long confirmationNumber, String executionChain2) {
		// TODO Auto-generated constructor stub
		this.rewardConfirmation = confirmationNumber;
		this.executionChain = executionChain2;
		
		
	}

	public long getRewardConfirmation() {
		return rewardConfirmation;
	}

	public void setRewardConfirmation(long rewardConfirmation) {
		this.rewardConfirmation = rewardConfirmation;
	}
	
	public String getExecutionChain() {
		return executionChain;
	}

	public void setExecutionChain(String executionChain) {
		this.executionChain = executionChain;
	}
}
