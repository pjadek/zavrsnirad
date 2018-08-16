package com.example.demo.resource;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Chat;
import com.example.demo.model.Event;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.model.UserByEvent;
import com.example.demo.model.UserByEventId;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.service.EventService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserByEventService;
import com.example.demo.service.UserService;

@RequestMapping("/app")
@SessionAttributes("user")
@Controller
public class EventController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private EventRestController eventRestController;
	
	@Autowired
	private UserByEventRestController userByEventRestController;
	
	@Autowired
	private UserByEventService userByEventService;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private SportRestController src;
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private EventRepository eventRepository;
	
	public EventController(UserService userService, EventService eventService) {
		this.userService = userService;
		this.eventService = eventService;
	}

	@RequestMapping(value = "/event/lat={lat}/lon={lon}/d={display}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnAllEventsForUser(@PathVariable("lat") double lat, @PathVariable("lon") double lon, @PathVariable("display") int d) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		User user = userService.findDataByID(auth.getName());
		List<Event> eventList = eventRestController.displayAllFutureEventsCloseToUser(lat, lon, user.getId());
		
		modelAndView.addObject("events", eventList);
		
		modelAndView.setViewName("event");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/event/{id}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView displayEvent(@PathVariable("id") long id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		UserByEvent userByEvent = userByEventRestController.pullUserEventData(user.getId(), id);
		Event eventData = eventRestController.displayEventData(id);
		
		if (eventData.getUser().getId() == user.getId()) {
			
			List<UserByEvent> approvedUsers = userByEventRestController.listUsersForSpecificEventByApprovall(id, 1);
			List<User> playerInfoApproved = new ArrayList<>();
			
			for (int i = 0; i < approvedUsers.size(); i++) {
				UserByEvent data = approvedUsers.get(i);
				User userData = userService.findById(data.getUserByEventId().getUser());
				playerInfoApproved.add(userData);
			}
			
			modelAndView.addObject("approved_players", playerInfoApproved);
			
			List<UserByEvent> pendindUsers = userByEventRestController.listUsersForSpecificEventByApprovall(id, 0);
			List<User> playerInfoPending = new ArrayList<>();
			
			for (int i = 0; i < pendindUsers.size(); i++) {
				UserByEvent data = pendindUsers.get(i);
				User userData = userService.findById(data.getUserByEventId().getUser());
				playerInfoPending.add(userData);
			}
			
			modelAndView.addObject("pending_players", playerInfoPending);
			modelAndView.addObject("org", "true");
			
		} else if (userByEvent == null) {
			modelAndView.addObject("hidden", "true");
			
		} else if (userByEvent.getApproved() == 0) {
			modelAndView.addObject("sub_not_approved", "tr");
			
		} else if (userByEvent.getApproved() == 1) {
			
			List<UserByEvent> approvedUsers = userByEventRestController.listUsersForSpecificEventByApprovall(id, 1);
			List<User> playerInfo = new ArrayList<>();
			
			for (int i = 0; i < approvedUsers.size(); i++) {
				UserByEvent data = approvedUsers.get(i);
				User userData = userService.findById(data.getUserByEventId().getUser());
				playerInfo.add(userData);
			}
			
			modelAndView.addObject("approved_players", playerInfo);
			modelAndView.addObject("sub_approved", "true");
		} 
		
		modelAndView.addObject("count", userByEventRestController.countAllApproved(id));
		modelAndView.addObject("event", eventRestController.displayEventData(id));
		modelAndView.addObject("usersApplied", userByEventRestController.showAllUsersAssignedToEvent(id));
		modelAndView.setViewName("single_event");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/event/{id}/submit-to-play", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView submitToPlay(@PathVariable("id") long event) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		
		UserByEventId userEventId = new UserByEventId(user.getId(), event);
		UserByEvent userByEvent = new UserByEvent();
		userByEvent.setUserByEventId(userEventId);
		userByEvent.setApproved(0);
		userByEvent.setScore(0);
		
		userByEventService.save(userByEvent);
		
		return new ModelAndView("redirect:/app/event/" + event + "?status=success");
	}
	
	@RequestMapping(value = "/event/{event}/approve/{user}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView approveUser(@PathVariable("event") long event, @PathVariable("user") long user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userData = userService.findDataByID(auth.getName());
		
		Event eventData = eventRestController.displayEventData(event);
		
		if (userData.getId() != eventData.getUser().getId()) {
			return new ModelAndView("redirect:/app/event/" + event + "?status=unauthorised");
		}
		
		UserByEvent userByEvent = userByEventRestController.pullUserEventData(user, event);
		userByEvent.setApproved(1);
		userByEventService.save(userByEvent);
		
		return new ModelAndView("redirect:/app/event/" + event + "?status=approved");
	}
	
	@RequestMapping(value = "/event/{event}/decline/{user}", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView declineUser(@PathVariable("event") long event, @PathVariable("user") long user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userData = userService.findDataByID(auth.getName());
		
		Event eventData = eventRestController.displayEventData(event);
		
		if (userData.getId() != eventData.getUser().getId()) {
			return new ModelAndView("redirect:/app/event/" + event + "?status=unauthorised");
		}
		
		UserByEvent userByEvent = userByEventRestController.pullUserEventData(user, event);
		userByEvent.setApproved(0);
		userByEventService.save(userByEvent);
		
		return new ModelAndView("redirect:/app/event/" + event + "?status=declined");
	}
	
	@RequestMapping("/event/my")
	@ResponseBody
	public ModelAndView displayMyEvents() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		User user = userService.findDataByID(auth.getName());
		List<Event> eventList = eventService.findMyEvents(user.getId());
		
		modelAndView.addObject("events", eventList);
		
		modelAndView.setViewName("event_my");
		
		return modelAndView;
	}
	
	@RequestMapping("/event/approved")
	@ResponseBody
	public ModelAndView displayApprovedEvents() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		User user = userService.findDataByID(auth.getName());
		List<Event> eventList = eventService.findMyApprovedEvents(user.getId());
		
		modelAndView.addObject("events", eventList);
		
		modelAndView.setViewName("event_approved");
		
		return modelAndView;
	}
	
	@RequestMapping("/event/interest")
	@ResponseBody
	public ModelAndView transferToInterest() {
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.setViewName("event_interest_push_location");
		
		return modelAndView;
	}
	
	@RequestMapping("/event/interest/lat={lat}/lon={lon}/d={d}")
	@ResponseBody
	public ModelAndView displayInterests(@PathVariable("lat") double lat, @PathVariable("lon") double lon, @PathVariable("d") int d) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		User user = userService.findDataByID(auth.getName());
		List<Event> eventList = eventService.findMyInterests(user.getId(), lat, lon, user.getRadius());
		
		modelAndView.addObject("events", eventList);
		
		modelAndView.setViewName("event_interest");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/event/new", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setupAddEventPage() {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		modelAndView.addObject("id", userService.findIdByUsername(auth.getName()));
		
		Event event = new Event();
		modelAndView.addObject("event", event);
		
		modelAndView.addObject("sportList", src.listAllSports());
		
		modelAndView.setViewName("event_add");
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/event/send-event", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView insertNewEvent(@ModelAttribute Event event) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findDataByID(auth.getName());
		event.setUser(user);
		
		eventRepository.save(event);
		
		return new ModelAndView("redirect:/app/event/my?status=success");
	}
	
}