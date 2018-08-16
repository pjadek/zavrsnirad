package com.example.demo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Chat;
import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.model.UserByEvent;
import com.example.demo.service.ChatService;
import com.example.demo.service.EventService;
import com.example.demo.service.UserByEventService;
import com.example.demo.service.UserService;

@RequestMapping("/app")
@SessionAttributes("user")
@Controller
public class ChatController {
	
	@Autowired
	private ChatService chatService;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserByEventService userByEventService;
	
	@RequestMapping(value = "/event/{event}/chat", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView displayChat(@PathVariable("event") long event) {
		
		//Find user
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		//Find if user has applied to event
		UserByEvent userByEvent = userByEventService.checkUserForEvent(event, user.getId());
		
		//Find event
		Event eventSearch = eventService.findById(event);
		
		//check if user is allowed to see chat
		if (eventSearch.getUser().getId() == user.getId()) {
			
		}
		else if ((userByEvent == null || userByEvent.getApproved() == 0)) {
			return new ModelAndView("redirect:/app");
		}
		
		ModelAndView modelAndView = new ModelAndView();
		
		Chat chat = new Chat();
		
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		modelAndView.addObject("msg", chatService.findByEventId(event));
		modelAndView.addObject("chat", chat);
		modelAndView.addObject("event", eventSearch);
		modelAndView.setViewName("chat_event");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/event/{event}/chat/send-message", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView sendMessage(@PathVariable("event") long eventId, @ModelAttribute Chat chat) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		Event eventSearch = eventService.findById(eventId);
		
		chat.setEvent(eventSearch);
		chat.setUser(user);
		
		chatService.save(chat);
		
		return new ModelAndView("redirect:/app/event/" + eventId + "/chat");
	}

}
