package com.rewardomain.rewardmanager.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
@Entity
public class Dining {
		@Id
		private long id;
		@Column
		@JsonProperty("credit_card_number")
		private String creditCardNumber;
		@Column
		@JsonProperty("merchant_number")
		private long merchantNumber;
		@Column
		@JsonProperty("dining_amount")
		private double diningAmount;
		@Column
		@JsonProperty("dining_date")
		private String diningDate;
		
		@OneToOne(mappedBy = "dining", cascade = CascadeType.ALL)
	    private Reward reward;

		public Dining() {
			super();
		}

		public Dining(long id, String creditCardNumber, long merchantNumber, double diningAmount, String diningDate) {
			super();
			this.id = id;
			this.creditCardNumber = creditCardNumber;
			this.merchantNumber = merchantNumber;
			this.diningAmount = diningAmount;
			this.diningDate = diningDate;
		}
		public long getId() {
			return id;
		}
		public String getCreditCardNumber() {
			return creditCardNumber;
		}
		public long getMerchantNumber() {
			return merchantNumber;
		}
		public double getDiningAmount() {
			return diningAmount;
		}
		public String getDiningDate() {
			return diningDate;
		}
		public void setId(long id) {
			this.id = id;
		}
		public void setCreditCardNumber(String creditCardNumber) {
			this.creditCardNumber = creditCardNumber;
		}
		public void setMerchantNumber(long merchantNumber) {
			this.merchantNumber = merchantNumber;
		}
		public void setDiningAmount(double diningAmount) {
			this.diningAmount = diningAmount;
		}
		public void setDiningDate(String diningDate) {
			this.diningDate = diningDate;
		}

		@Override
		public String toString() {
			return "Dining [id=" + id + ", creditCardNumber=" + creditCardNumber + ", merchantNumber=" + merchantNumber
					+ ", diningAmount=" + diningAmount + ", diningDate=" + diningDate + "]";
		}
		
		
		
}
