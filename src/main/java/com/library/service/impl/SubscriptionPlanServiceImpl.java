package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.mapper.SubscriptionPlanMapper;
import com.library.modal.SubscriptionPlan;
import com.library.modal.User;
import com.library.payload.dto.SubscriptionPlanDTO;
import com.library.repository.SubscriptionPlanRepository;
import com.library.service.SubscriptionPlanService;
import com.library.service.UserService;
import com.library.service.subscriptionPlanDTO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

	private final SubscriptionPlanRepository planRepository;
	private final SubscriptionPlanMapper planMapper;
	private final UserService userService;
	
	@Override
	public SubscriptionPlanDTO createSubscriptionPlan(SubscriptionPlanDTO planDTO) throws Exception {
		// TODO Auto-generated method stub
		if(planRepository.existsByPlanCode(planDTO.getPlanCode())) {
			throw new Exception("Plancode is already exists");
		}
		SubscriptionPlan plan=planMapper.toEntity(planDTO);
		User currentUser=userService.getCurrentUser();
		plan.setCreatedBy(currentUser.getFullName());
		plan.setUpdatedBy(currentUser.getFullName());
		SubscriptionPlan savedPlan=planRepository.save(plan);
		return planMapper.toDTO(savedPlan);
	}

	@Override
	public SubscriptionPlanDTO updateSubscriptionPlan(Long planId, SubscriptionPlanDTO planDTO) {
		// TODO Auto-generated method stub
		SubscriptionPlan exisitingPlan=planRepository.findById(planId).orElseThrow(
				()->new Exception("plan not found!")
				);
		planMapper.updateEntity(exisitingPlan, planDTO);
		User currentUser=userService.getCurrentUser();
		SubscriptionPlan updatePlan=planRepository.save(exisitingPlan);
		return null;
	}

	@Override
	public void deleteSubscriptionPlan(Long PlanId) {
		// TODO Auto-generated method stub
		SubscriptionPlan exisitingPlan=planRepository.findById(planId).orElseThrow(
				()->new Exception("plan not found!")
				);
		planRepository.delete(exisitingPlan);
		
	}

	@Override
	public List<subscriptionPlanDTO> getAllSubscriptionPlan() {
		// TODO Auto-generated method stub
		List<SubscriptionPlan> planList=planRepository.findAll();
		
		return planList.stream().map(
				planMapper::toDTO
				).collect(Collectors.toList());		
	}

	@Override
	public SubscriptionPlanDTO getSubscriptionPlan(Long planId) {
		// TODO Auto-generated method stub
		return null;
	}

}
