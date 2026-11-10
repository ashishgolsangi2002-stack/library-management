package com.library.service.impl;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.library.exception.SubscriptionException;
import com.library.mapper.SubscriptionMapper;
import com.library.modal.Subscription;
import com.library.modal.SubscriptionPlan;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionDTO;
import com.library.repository.SubscriptionPlanRepository;
import com.library.repository.SubscriptionRepository;
import com.library.service.SubscriptionService;
import com.library.service.UserService;

public class SubscriptionImpl implements SubscriptionService {

	private final SubscriptionRepository subscriptionRepository;
	private final SubscriptionMapper subscriptionMapper; 
	private final UserService userService;
	private final SubscriptionPlanRepository subscriptionPlanRepository;
	
	@Override
	public SubscriptionDTO subscribe(SubscriptionDTO subscriptionDTO) {
		// TODO Auto-generated method stub
		User user=userService.getCurrentUser();
		
		SubscriptionPlan plan=subscriptionPlanRepository
				.findById(subscriptionDTO.getPlanId()).orElseThrow(
						()->new Subscription("plan not found")
						);
		
		//Optional<sub>
		
		Subscription subscription= subscriptionMapper.toEntity(subscriptionDTO);
		subscription.initializeFromPlan();
		Subscription savedSubscription=subscriptionRepository.save(subscription);
		//create payment (todo)
		return subscriptionMapper.toDTO(savedSubscription);
	}

	@Override
	public SubscriptionDTO getUserActiveSubscription(Long userId) throws Exception {
		// TODO Auto-generated method stub
		User user=userService.getCurrentUser();
		Subscription subscription= subscriptionRepository
				.findActiveSubscriptionByUserId(user.getId(),LocalDate.now())
				.orElseThrow(()->new SubscriptionException("no active subscription found"));
		
		return null;
	}

	@Override
	public SubscriptionDTO cancelSubscription(Long subscriptionId, String reason) {
		// TODO Auto-generated method stub
		Subscription subscription = subscriptionRepository.findById(subscriptionId)
		        .orElseThrow(() -> new SubscriptionException(
		                "Subscription not found with ID: " + subscriptionId));

		if (!subscription.getIsActive()) {
		    throw new SubscriptionException("Subscription is already inactive");
		}

		// Mark as cancelled
		subscription.setIsActive(false);
		subscription.setCancelledAt(LocalDateTime.now());
		subscription.setCancellationReason(reason != null ? reason : "Cancelled by user");

		subscription = subscriptionRepository.save(subscription);
		
		log.info("Subscription cancelled successfully {}:", subscriptionId);

		return subscriptionMapper.toDTO(subscription);
	}

	@Override
	public SubscriptionDTO activeSubscription(Long subscriptionId, Long paymentId) {
		// TODO Auto-generated method stub
		Subscription subscription= subscriptionRepository.findById(subscriptionId)
				.orElseThrow(
						()-> new SubscriptionException("subscription not found by id!")
						);
		
		//verify payment (todo)
		
		subscription.setIsActive(true);
		subscription.setStartDate(LocalDate.now());
		subscription.calculateEndDate();
		return subscriptionMapper.toDTO(subscription);
	}

	@Override
	public List<SubscriptionDTO> getAllSubscription(Pageable pageable) {
		// TODO Auto-generated method stub
		List<Subscription> subscriptionRepository.findAll();
		return subscriptionMapper.toDTOList(subscriptions);
		
	}
	
	@Override
	public void deactivateExpiredSubscription() throws Exception{
		List<Subscription> expiredSubscriptions=subscriptionRepository
				.findExpiredActiveSubscription(LocalDate.now());
		
			 for(Subscription subscription:expiredSubscriptions) {
				 subscription.setIsActive(false);
				 subscriptionRepository.save(subscription);
			 }
	
	}
}



