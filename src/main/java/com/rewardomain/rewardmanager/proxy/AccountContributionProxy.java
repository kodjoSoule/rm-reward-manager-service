package com.rewardomain.rewardmanager.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.rewardomain.rewardmanager.bean.AccountContributionResponse;

//@FeignClient(name="benefit-calculation", url="localhost:8400")
@FeignClient(name="account-contribution-service")
public interface AccountContributionProxy {
	@PutMapping("/account-contribution/{credit_card_number}/reward/{reward}")
	public AccountContributionResponse distributeReward ( 
			@PathVariable("credit_card_number") String number, 
			@PathVariable double reward);
}
