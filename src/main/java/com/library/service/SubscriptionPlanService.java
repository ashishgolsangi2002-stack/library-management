package com.library.service;

import java.util.List;

import com.library.payload.dto.SubscriptionPlanDTO;

public interface SubscriptionPlanService {
	
SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO);

SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO );

void deleteSubscriptionPlan(Long PlanId);

List<subscriptionPlanDTO> getAllSubscriptionPlan();

SubscriptionPlanDTO getSubscriptionPlan(Long planId);

}
