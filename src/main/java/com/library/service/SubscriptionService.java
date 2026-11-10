package com.library.service;

import java.awt.print.Pageable;
import java.util.List;

import com.library.payload.dto.SubscriptionDTO;

public interface SubscriptionService { //Once we implement payment, it returns payment initiation response
	
	SubscriptionDTO subscribe(SubscriptionDTO subscriptionDTO);
	
	SubscriptionDTO getUserActiveSubscription(Long userId);
	
	SubscriptionDTO cancelSubscription(Long subscriptionId, String reason);
	
	SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId);
	
	List<SubscriptionDTO> getAllSubscription(Pageable pageable);
	
	void deactivateExpiredSubscriptions(Long userId) throws Exception;

	void deactivateExpiredSubscription() throws Exception;

	List<SubscriptionDTO> getAllSubscriptions();
	
	}
