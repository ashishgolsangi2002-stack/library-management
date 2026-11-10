package com.library.controller;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.payload.dto.SubscriptionDTO;
import com.library.service.SubscriptionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscriptions")
public class SubscriptionController {
	private final SubscriptionService subscriptionService;
	
	
	@PostMapping("/subscribe")
	public ResponseEntity<?> subscribe(
		@Valid @RequestBody SubscriptionDTO subscription	
			)throws Exception{
		SubscriptionDTO dto= subscriptionService.subscribe(subscription);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("/admin")
	public ResponseEntity<?> getAllSubscriptions(){
		int page=0;
		int size=10;
		Pageable pageable=PageRequest.of(page, size);
		List<SubscriptionDTO> dtoList= subscriptionService.getAllSubscriptions();
		return ResponseEntity.ok(dtoList);
	}
	
	@GetMapping("/admin/deactivate-expired")
	public ResponseEntity<?> deactivateExpiredSubscriptions(){
		int page=0;
		int size=10;
		Pageable pageable=PageRequest.of(page, size);
		List<SubscriptionDTO> dtoList= subscriptionService.deactivateExpiredSubscriptions();
		
		return ResponseEntity.ok(dtoList);
	}
	
	
}
