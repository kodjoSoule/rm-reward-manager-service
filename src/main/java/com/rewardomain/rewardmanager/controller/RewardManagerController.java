package com.rewardomain.rewardmanager.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.rewardomain.rewardmanager.bean.AccountContributionResponse;
import com.rewardomain.rewardmanager.bean.BenefitRestaurant;
import com.rewardomain.rewardmanager.bean.Confirmation;
import com.rewardomain.rewardmanager.bean.Dining;
import com.rewardomain.rewardmanager.bean.Reward;
import com.rewardomain.rewardmanager.proxy.AccountContributionProxy;
import com.rewardomain.rewardmanager.proxy.BenefitCalculationProxy;
import com.rewardomain.rewardmanager.repository.DiningRepository;
import com.rewardomain.rewardmanager.repository.RewardRepository;

import ch.qos.logback.classic.Logger;
import io.github.resilience4j.retry.annotation.Retry;

@CrossOrigin
@RestController
public class RewardManagerController {
	
	@Autowired
	private RewardRepository repository;

	
	@Autowired
	private DiningRepository repositoryDining;

	
	@Autowired
	private BenefitCalculationProxy proxy;
	
	@Autowired
	private AccountContributionProxy accountContributionProxy;
	
	
	
	@Autowired
	private Environment environment;
	
	
	
	
	public ResponseEntity<ErrorReponse> defaultResponse (RuntimeException e) { 
		HttpStatusCode httpStatusCode =HttpStatusCode.valueOf(404); 
		return new ResponseEntity<ErrorReponse>(new ErrorReponse(404L, e.getMessage()), httpStatusCode);
	}

	
	@GetMapping("/reward-manager/hello")
	public String hello() {
		return "Hello World";
	}
	@GetMapping("/reward-manager/creditTest")
	public ResponseEntity<Object> getCreditCard() {
		Map<String, Object> responseBody = new HashMap<>();
    	//String creditCard = dining.getCreditCardNumber();
		responseBody.put("credt_card_number",12);
		responseBody.put("dining_id", 12);
		//AccountContributionResponse accountContributionResponse = accountContributionProxy
		
    	//CreditCard creditCard = account.getCreditCard();
        return new ResponseEntity<Object>(responseBody, HttpStatusCode.valueOf(200));
	}
//	liste de recompense par carde de credit
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@GetMapping("/reward-manager/rewards/credit-card/{creditCardNumber}")
	public ResponseEntity<Object> getRewardsByCreditCardNumber(@PathVariable String creditCardNumber) {
		System.out.println("Number of credit card : "+creditCardNumber);
		List<Reward> rewards =repository.findByCreditCardNumber(creditCardNumber); 
				//new ArrayList<Reward>() ;
//		rewards.add(new Reward(1, 100, 100, 1, LocalDateTime.now()));
//		rewards.add(new Reward(2, 200, 200, 2, LocalDateTime.now()));
//		rewards.add(new Reward(3, 300, 300, 3, LocalDateTime.now()));
		
		repository.findByCreditCardNumber(creditCardNumber);
		
		
		return new ResponseEntity<>(rewards, HttpStatus.OK);
	}
	//
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@PostMapping("/reward-manager-old/rewards")
	public ResponseEntity<Confirmation> createRewardold(@RequestBody Dining dining) {
//		show dining
		System.out.println(dining);
		long merchantNumber = dining.getMerchantNumber();
		double diningAmount = dining.getDiningAmount();
		//Benefit calculation invoked
		BenefitRestaurant benefitRestaurant = proxy.getBenefitRestaurant(merchantNumber, diningAmount);
		System.out.println(benefitRestaurant.getExecutionChain());
		String port = environment.getProperty("local.server.port");
		repositoryDining.save(dining);
		Reward reward = new Reward (
				dining.getId()+100,
				dining.getId()+1000,
				benefitRestaurant.getAmount(),
				merchantNumber,
				LocalDateTime.parse(dining.getDiningDate(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))		
				);
			//Save the reward in the
			repository.save(reward);
			
			
			HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(200);

			
			Confirmation confirmation = new Confirmation(reward.getConfirmationNumber(),benefitRestaurant.getExecutionChain() );
			confirmation.setExecutionChain(
					"reward-manager instance - " + port + " == invoked => " + benefitRestaurant.getExecutionChain()
			);	
			return new ResponseEntity<Confirmation>(confirmation, httpStatusCode);
	}
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@PostMapping("/reward-manager/rewards")
	public ResponseEntity<Confirmation> createReward(@RequestBody Dining dining) {
		long merchantNumber = dining.getMerchantNumber();
		double diningAmount = dining.getDiningAmount();
		//Benefit calculation invoked
		BenefitRestaurant benefitRestaurant = proxy.getBenefitRestaurant(merchantNumber, diningAmount);
		
		String port = environment.getProperty("local.server.port");
		repositoryDining.save(dining);
		System.out.println(dining);
		Reward reward = new Reward (
				dining.getId()+100,
				dining.getId()+1000,
				benefitRestaurant.getAmount(),
				merchantNumber,
				LocalDateTime.parse(dining.getDiningDate(),
						DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'"))		
				);
			reward.setDining(dining);
			//Save the reward in the
			repository.save(reward);
					
			HttpStatusCode httpStatusCode = HttpStatusCode.valueOf(200);
	
			
			Confirmation confirmation = new Confirmation(reward.getConfirmationNumber(),benefitRestaurant.getExecutionChain() );
			confirmation.setExecutionChain(
					"reward-manager instance - " + port + " == invoked => " + benefitRestaurant.getExecutionChain()
			);	
	
			return new ResponseEntity<Confirmation>(confirmation, httpStatusCode);
	}
	
	//liste des recompense par number de compte
	@GetMapping("/rewards/{accountNumber}")
	public ResponseEntity<List<Reward>> getRewardsByAccountNumber(@PathVariable String accountNumber) {
		List<Reward> rewards = new ArrayList<Reward>() ;
		
	    return new ResponseEntity<>(rewards, HttpStatusCode.valueOf(200));
	}
	
	
	@GetMapping("/reward-manager/creditcard")
    public ResponseEntity<Object> getDiningByCrediCard (
    		@PathVariable("creditCardNumber") String number)
    { 
    	
//    	Dining dining = repositoryDining.findByCreditCardNumber(number);
    	// Créer un objet Map pour représenter le corps de la réponse JSON
		
    	Map<String, Object> responseBody = new HashMap<>();
//    	String creditCard = dining.getCreditCardNumber();
		responseBody.put("credt_card_number",12);
		responseBody.put("dining_id", 12);
    	
    	//CreditCard creditCard = account.getCreditCard();
        return new ResponseEntity<Object>(responseBody, HttpStatusCode.valueOf(200));
    }
	
	@RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public ResponseEntity<?> handleOptionsRequest() {
        return ResponseEntity.ok().build();
    }

	
	
	//get all rewards
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@GetMapping("/reward-manager/rewards")
	public ResponseEntity<Object> getRewards() {
		return ResponseEntity.status(200).body(repository.findAll());
	}
	//TODO
	/*
	 * 
	 * TO DO $2
	 * 19.Lecture des informations concernant une récompense précise (reward
	 * 
	 * */
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@GetMapping("/reward-manager/rewards/{reward_confirmation_number}")
	public Reward getRewardByMechantNumber(@PathVariable("reward_confirmation_number") long confirmationNumber) {
		return repository.findByConfirmationNumber(confirmationNumber);	
	}
	@Retry(name="reward-manager",fallbackMethod ="defaultResponse")
	@PostMapping("/reward-manager/rewards/{credit_card_number}/{reward_confirmation_number}")
    public AccountContributionResponse distribution(@PathVariable("reward_confirmation_number") long reward_confirmation_number,@PathVariable("credit_card_number") String credit_card_number) {

        Reward reward = repository.findByConfirmationNumber(reward_confirmation_number);

        String port = environment.getProperty("local.server.port");
		
        if (reward == null) {
            throw new RuntimeException("Recompense non trouvée !");
        }
        
        double amount = reward.getAmount();
        
        AccountContributionResponse accountContributionResponse = accountContributionProxy.distributeReward(credit_card_number,amount);
        accountContributionResponse.setExecutionChain("reward-service instance: " + port + "==invoked => " + accountContributionResponse.getExecutionChain());
       
        return 	accountContributionResponse;
        
       
       
    }
	// Endpoint pour trouver les récompenses par numéro de carte de crédit
//    @GetMapping("/reward-manager/rewards/by-credit-card/{creditCardNumber}")
//    public ResponseEntity<List<Reward>> getRewardsByCreditCardNumber(@PathVariable String creditCardNumber) {
//        List<Reward> rewards = repository.findByCreditCardNumber(creditCardNumber);
//        return new ResponseEntity<>(rewards, HttpStatus.OK);
//    }

	
}
