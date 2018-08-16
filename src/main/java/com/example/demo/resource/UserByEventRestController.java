package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.UserByEvent;
import com.example.demo.service.UserByEventService;

@RestController
@RequestMapping("/api/rest")
public class UserByEventRestController {
	
	@Autowired
	private UserByEventService userByEventService;
	
	public UserByEventRestController(UserByEventService userByEventService) {
		this.userByEventService = userByEventService;
	}
	
	@GetMapping("/user/event/{id}/{approved}")
	public List<UserByEvent> listUsersForSpecificEventByApprovall(@PathVariable("id") long event, @PathVariable("approved") int approved) {
		return userByEventService.findAllByEventAndApproved(event, approved);
	}
	
	@GetMapping("/user/event/{user}/score")
	public float showUsersScore(@PathVariable("user") long user) {
		return userByEventService.findAveargeScoreByUserByEventIdUser(user);
	}
	
	@GetMapping("/user/event/{id}/all")
	public List<UserByEvent> showAllUsersAssignedToEvent(@PathVariable("id") long id) {
		return userByEventService.checkIfUserIsApprovedForEvent(id);
	}
	
	@GetMapping("/user/event/{id}/count")
	public int countAllApproved(@PathVariable("id") long id) {
		return userByEventService.findAllUsersApprovedForEvent(id);
	}
	
	@GetMapping("/user/{user}/event/{event}")
	public UserByEvent pullUserEventData(@PathVariable("user") long user, @PathVariable("event") long event) {
		return userByEventService.checkUserForEvent(event, user);
	}
	
	@PostMapping("/user/event/apply")
	public void applyForEvent(@RequestBody UserByEvent userByEvent) {
		userByEventService.save(userByEvent);
	}

}
