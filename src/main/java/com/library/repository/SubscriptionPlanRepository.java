package com.library.repository;

public interface SubscriptionPlanRepository extends JpaRepository<SubscriptionPlan, Long> {

	Boolean existsByPlanCode(String planCode);
	
}
