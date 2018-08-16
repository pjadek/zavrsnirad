package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.repository.UserRepository;

@RequestMapping("")
@SessionAttributes("user")
@Controller
public class IndexController {

	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping("/app")
	public ModelAndView setupIndexPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("id", userRepository.findIdByUsername(auth.getName()));
		
		modelAndView.setViewName("index");
		return modelAndView;
	}
}
