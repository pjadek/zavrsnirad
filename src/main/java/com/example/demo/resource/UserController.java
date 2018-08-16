package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Interest;
import com.example.demo.model.InterestId;
import com.example.demo.model.User;
import com.example.demo.repository.InterestRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RequestMapping("app/")
@SessionAttributes("user")
@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRestController urc;
	
	@Autowired
	private SportRestController src;
	
	@Autowired
	private InterestRestController irc;
	
	@Autowired
	private InterestRepository interestRepository;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value = "account/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setupAccountPage(@PathVariable("id") long id) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userCheck = userService.findDataByID(auth.getName());
		
		if (userCheck.getId() != id) {
			id = userCheck.getId();
		}
		
		
		ModelAndView modelAndView = new ModelAndView();
		User user = urc.pushUserData(id);
		
		modelAndView.addObject("user", user);
		modelAndView.addObject("pass", user);
		modelAndView.addObject("id", user);
		modelAndView.addObject("userRange", user);
		modelAndView.addObject("sportList", src.listAllSports());
		modelAndView.addObject("userByInterest", new Interest());
		modelAndView.setViewName("account");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "account/updateuser", method = RequestMethod.POST)
	@ResponseBody
	public final ModelAndView updateUserData(@ModelAttribute User user) {
		userRepository.save(user);
		long userID = user.getId();
		return new ModelAndView("redirect:/app/account/" + userID + "?status=success");
	}
	
	@RequestMapping(value = "account/avatar", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setupAvatarPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		modelAndView.setViewName("set_avatar");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "account/avatar/set/{avatar_id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView changeAvatar(@PathVariable("avatar_id") String avatarId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		String avatar = avatarId + ".png";
		user.setPictureUrl(avatar);
		userRepository.save(user);
		
		return new ModelAndView("redirect:/app/account/" + user.getId() + "?status=success");
	}
	
	@RequestMapping(value = "account/set-pwd", method = RequestMethod.POST)
	@ResponseBody
	public final ModelAndView updatePassword(@ModelAttribute User user) {
		userRepository.save(user);
		long userID = user.getId();
		return new ModelAndView("redirect:/app/account/" + userID + "?status=success");
	}
	
	@RequestMapping(value = "account/set-interest", method = RequestMethod.POST)
	@ResponseBody
	public final ModelAndView setInterest(@RequestParam("checked") List<Long> sportsOfInterests) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		List<Interest> interestsForUser = irc.showAllInterestForUser(user.getId());
		
		for (int i = 0; i < interestsForUser.size(); i++) {
			Interest interest = interestsForUser.get(i);
			interestRepository.delete(interest);
		}
		
		for (int i = 0; i < sportsOfInterests.size(); i++) {
			InterestId id = new InterestId(sportsOfInterests.get(i), user.getId());
			Interest interest = interestRepository.save(new Interest(id));
		}
				
		return new ModelAndView("redirect:/app/event/interest");
	}
	

	@RequestMapping(value = "account/set-radius", method = RequestMethod.POST)
	@ResponseBody
	public final ModelAndView setRadius(@ModelAttribute User userObject) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		double radius = userObject.getRadius();
		radius = radius * 1000 * 0.00001362;
		
		user.setRadius(radius);
		userRepository.save(user);
				
		return new ModelAndView("redirect:/app/account/" + user.getId() + "?status=success");
	}

}
