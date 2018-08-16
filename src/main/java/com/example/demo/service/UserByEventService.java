package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.UserByEvent;
import com.example.demo.repository.UserByEventRepository;

@Service
@Component
public class UserByEventService {

	@Autowired
	private UserByEventRepository userByEventRepository;

	public List<UserByEvent> findAllByEventAndApproved(long l, int i) {
		return userByEventRepository.findAllByEventAndApproved(l, i);
	}
	
	public float findAveargeScoreByUserByEventIdUser(long u) {
		return userByEventRepository.findAveargeScoreByUserByEventIdUser(u);
	}
	
	public void save(UserByEvent userByEvent) {
		userByEventRepository.save(userByEvent);
	}
	
	public List<UserByEvent> checkIfUserIsApprovedForEvent(long event) {
		return userByEventRepository.checkIfUserIsApprovedForEvent(event);
	}
	
	public UserByEvent checkUserForEvent(long event, long user) {
		return userByEventRepository.checkUserForEvent(event, user);
	}
	
	public int findAllUsersApprovedForEvent(long event) {
		return userByEventRepository.findAllUsersApprovedForEvent(event);
	}

}
