package com.rewardomain.rewardmanager.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BenefitRestaurant {
	@JsonProperty("benefit_amount")
	private double amount;
	@JsonProperty("execution_chain")
	String executionChain;
	public BenefitRestaurant() { }
	
	
	
	public BenefitRestaurant(double amount) {
		this.setAmount(amount) ;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getExecutionChain() {
		return executionChain;
	}

	public void setExecutionChain(String executionChain) {
		this.executionChain = executionChain;
	}
	
}