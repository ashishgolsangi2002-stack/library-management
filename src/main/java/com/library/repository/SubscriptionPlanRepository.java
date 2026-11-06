package com.library.repository;

import com.library.modal.SubscriptionPlan;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

	Boolean existsByPlanCode(String planCode);
	
}
