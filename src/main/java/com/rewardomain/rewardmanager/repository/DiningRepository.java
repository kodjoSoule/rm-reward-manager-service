package com.rewardomain.rewardmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rewardomain.rewardmanager.bean.Dining;
import com.rewardomain.rewardmanager.bean.Reward;

public interface DiningRepository extends JpaRepository<Dining, Long>{
	public Dining findByCreditCardNumber(String creditCardNumber);
}
