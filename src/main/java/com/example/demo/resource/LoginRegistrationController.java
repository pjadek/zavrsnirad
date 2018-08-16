package com.example.demo.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RequestMapping("")
@Controller
public class LoginRegistrationController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value={"/login"}, method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView setupRegisterPage() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	@ResponseBody
	public final ModelAndView registerNewUser(@ModelAttribute User user) {
		List<User> userList = userRepository.findIdByUsername(user.getUsername());
		
		if (userList.isEmpty()) {
			user.setRadius(0.100124); //Default radius of around 8 km
			userRepository.save(user);
			return new ModelAndView("redirect:/login?status=success");
		}
		else {
			return new ModelAndView("redirect:/register?status=fail");
		}
	}
	

}
