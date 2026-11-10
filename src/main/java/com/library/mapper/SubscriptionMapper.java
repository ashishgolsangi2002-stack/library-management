package com.library.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.library.exception.SubscriptionException;
import com.library.modal.Subscription;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionDTO;
import com.library.repository.SubscriptionPlanRepository;
import com.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SubscriptionMapper {

	private final UserRepository userRepository;
	private final SubscriptionPlanRepository planRepository;

	/**
	 * Convert Subscription entity to DTO
	 */
	public SubscriptionDTO toDTO(Subscription subscription) {
	    if (subscription == null) {
	        return null;
	    }

	    SubscriptionDTO dto = new SubscriptionDTO();
	    dto.setId(subscription.getId());
	    
	 // User information
	    if (subscription.getUser() != null) {
	        dto.setUserId(subscription.getUser().getId());
	        dto.setUserName(subscription.getUser().getFullName());
	        dto.setUserEmail(subscription.getUser().getEmail());
	    }

	    // Plan information
	    if (subscription.getPlan() != null) {
	        dto.setPlanId(subscription.getPlan().getId());
	    }
	    dto.setPlanName(subscription.getPlanName());
	    dto.setPlanCode(subscription.getPlanCode());
	    dto.setPrice(subscription.getPrice());
	   
	    dto.setStartDate(subscription.getStartDate());
	    dto.setEndDate(subscription.getEndDate());
	    dto.setIsActive(subscription.getIsActive());
	    dto.setMaxBooksAllowed(subscription.getMaxBooksAllowed());
	    dto.setMaxDaysPerBook(subscription.getMaxDaysPerBook());
	    dto.setAutoRenew(subscription.getAutoRenew());
	    dto.setCancelledAt(subscription.getCancelledAt());
	    dto.setCancellationReason(subscription.getCancellationReason());
	    dto.setNotes(subscription.getNotes());
	    dto.setCreatedAt(subscription.getCreatedAt());
	    dto.setUpdatedAt(subscription.getUpdatedAt());

	    // Calculated fields
	    dto.setDaysRemaining(subscription.getDaysRemaining());
	    dto.setIsValid(subscription.isValid());
	    dto.setIsExpired(subscription.isExpired());

	    return dto;
}
	
	 
	public Subscription toEntity(SubscriptionDTO dto) throws SubscriptionException {
	    if (dto == null) {
	        return null;
	    }

	    Subscription subscription = new Subscription();
	    subscription.setId(dto.getId());

	    // Map user
	    if (dto.getUserId() != null) {
	        User user = userRepository.findById(dto.getUserId())
	                .orElseThrow(() -> new SubscriptionException("User not found with ID: ""));
	        subscription.setUser(user);
	    }
	    
	    // Map plan
	    if (dto.getPlanId() != null) {
	        SubscriptionPlan plan = planRepository.findById(dto.getPlanId())
	                .orElseThrow(() -> new SubscriptionException("Subscription plan not fou"));
	        subscription.setPlan(plan);
	    }

	    subscription.setPlanName(dto.getPlanName());
	    subscription.setPlanCode(dto.getPlanCode());
	    subscription.setPrice(dto.getPrice());
	    subscription.setCurrency(dto.getCurrency());
	    subscription.setStartDate(dto.getStartDate());
	    subscription.setEndDate(dto.getEndDate());
	    subscription.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
	    subscription.setMaxBooksAllowed(dto.getMaxBooksAllowed());
	    subscription.setMaxDaysPerBook(dto.getMaxDaysPerBook());
	    subscription.setAutoRenew(dto.getAutoRenew() != null ? dto.getAutoRenew() : false);
        subscription.setCancelledAt(dto.CancelledAt());
	
}
	public List<SubscriptionDTO> toDTOList(List<Subscription> subscriptions) {
	    if (subscriptions == null) {
	        return null;
	    }
	    return subscriptions.stream()
	            .map(this::toDTO)
	            .collect(Collectors.toList());
	}

}
