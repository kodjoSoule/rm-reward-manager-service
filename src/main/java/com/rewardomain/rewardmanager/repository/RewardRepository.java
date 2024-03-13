package com.rewardomain.rewardmanager.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rewardomain.rewardmanager.bean.Reward;

public interface RewardRepository extends JpaRepository<Reward, Long> {	
	//crud
	//findAll
	public List<Reward> findAll();
	//findById
	public Reward findById(long id);
	//deleteById
	public void deleteById(long id);
	//save
	public Reward save(Reward reward);
	// find by Confirmation Number
	public Reward findByConfirmationNumber(long confirmationNumber);
	
	//liste de recompense par carte de credit findByCreditCardNumber
//	public List<Reward> findByCreditCardNumber(String creditCardNumber);
	//requete hql pour recuperer les recompenses par carte de credit
	//card de credit est un attribut de la classe dining
	@Query("select r from Reward r join r.dining d where d.creditCardNumber = :creditCardNumber")
	public List<Reward> findByCreditCardNumber(@Param("creditCardNumber") String creditCardNumber);
}
