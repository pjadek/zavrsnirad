package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.service.UserService;



@RequestMapping("/api/rest")
@RestController
public class UserRestController {
	
	@Autowired
	private UserService userService;
	
	public UserRestController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/account/{id}")
	public User pushUserData(@PathVariable("id") long id) {
		User user = new User();
		user = userService.findById(id);
		
		if (user == null) {
			return null;
		}
		
		return user;
	}
	

}
