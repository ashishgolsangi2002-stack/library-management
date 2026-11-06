package com.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.payload.dto.SubscriptionPlanDTO;
import com.library.service.SubscriptionPlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/subscription-plans")
@RestController
@RequiredArgsConstructor
public class SubscriptionPlanController {

	private final SubscriptionPlanService subscriptionPlanService;
	
	@GetMapping
	public ResponseEntity<?> getAllSubscriptionPlans(){
		List<SubscriptionPlanDTO> plans= subscriptionPlanService.getAllSubscriptionPlan();
		return ResponseEntity.ok(plans);
	}
	
	@PostMapping("/admin/create")
	public ResponseEntity<?> createSubscriptionPlan(
			@Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO
			) throws Exception{
		List<SubscriptionPlanDTO>plans= subscriptionPlanService.getAllSubscriptionPlan(
				subscriptionPlanDTO;
				);
		return ResponseEntity.ok(plans);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updateSubscriptionPlan(
			@Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO
			@PathVariable long id
			) throws Exception{
		List<SubscriptionPlanDTO>plans= subscriptionPlanService.updateSubscriptionPlan(
				subscriptionPlanDTO;
				);
		return ResponseEntity.ok(plans);
	}
	
	@PutMapping("/admin/{id}")
	public ResponseEntity<?> updateSubscriptionPlan(
			@Valid @RequestBody SubscriptionPlanDTO subscriptionPlanDTO
			@PathVariable long id
			) throws Exception{
		List<SubscriptionPlanDTO>plans= subscriptionPlanService.updateSubscriptionPlan(
				subscriptionPlanDTO;
				);
		return ResponseEntity.ok(plans);
	}
	
	@DeleteMapping("/admin/{id}")
	public ResponseEntity<?> deleteSubscriptionPlan(
			
			@PathVariable long id
			) throws Exception{
		List<SubscriptionPlanDTO>plans= subscriptionPlanService.deleteSubscriptionPlan(id);
				
		return ResponseEntity.ok(plans);
	}
}
