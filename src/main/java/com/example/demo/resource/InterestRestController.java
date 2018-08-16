package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Interest;
import com.example.demo.service.InterestService;

@RequestMapping("/api/rest")
@RestController
public class InterestRestController {
	
	@Autowired
	private InterestService interestService;
	
	public InterestRestController(InterestService interestService) {
		this.interestService = interestService;
	}
	
	@GetMapping("/interest/user/{id}")
	public List<Interest> showAllInterestForUser(@PathVariable("id") long id) {
		return interestService.findByUser(id);
	}

}
