package com.rewardomain.rewardmanager.bean;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Reward {
	@Id
	private long Id;
	@Column(name="confirmation_number")
	@JsonProperty("reward_confirmation_number")
	private long confirmationNumber;
	@JsonProperty("reward_amount")
	private double amount;
	@Column(name="merchant_number")
	@JsonProperty("merchant_number")
	private long merchantNumber;
	
	@Column(name="reward_date")
	@JsonProperty("reward_date")
	private LocalDateTime rewardDate;
	
//	validite de recompense un boolean, par deefaut true
		@Column
		@JsonProperty("validity")
		private boolean validity = true;
		
	
	
	
	
	@OneToOne
    @JoinColumn(name = "dining_id", referencedColumnName = "id")
    private Dining dining;
	
	
	public Reward() {
		super();
	}

	public Reward(long id, long confirmationNumber, double amount, long merchantNumber, LocalDateTime rewardDate) {
		this.Id = id;
		this.confirmationNumber = confirmationNumber;
		this.amount = amount;
		this.merchantNumber = merchantNumber;
		this.rewardDate = rewardDate;
	}
	
	public long getId() {
		return Id;
	}
	public long getConfirmationNumber() {
		return confirmationNumber;
	}
	public double getAmount() {
		return amount;
	}
	public long getMerchantNumber() {
		return merchantNumber;
	}
	public LocalDateTime getRewardDate() {
		return rewardDate;
	}
	public void setId(long id) {
		Id = id;
	}
	public void setConfirmationNumber(long confirmationNumber) {
		this.confirmationNumber = confirmationNumber;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public void setMerchantNumber(long merchantNumber) {
		this.merchantNumber = merchantNumber;
	}
	public void setRewardDate(LocalDateTime rewardDate) {
		this.rewardDate = rewardDate;
	}

	public void setDining(Dining dining) {
        this.dining = dining;
    }

	public Dining getDining() {
		return dining;
	}
	
	
}
