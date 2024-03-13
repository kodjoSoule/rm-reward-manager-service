package com.rewardomain.rewardmanager.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.rewardomain.rewardmanager.bean.BenefitRestaurant;

//@FeignClient(name="account-contribution", url="localhost:8400")
@FeignClient(name="benefit-calculation-service")
public interface BenefitCalculationProxy {
	@GetMapping("/benefit-calculation/{merchant_number}/{dining_amount}")
	public BenefitRestaurant getBenefitRestaurant (
			@PathVariable("merchant_number") long number,
			@PathVariable("dining_amount") double amount);
	
}
