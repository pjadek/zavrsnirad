package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Event;
import com.example.demo.model.User;
import com.example.demo.service.EventService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/rest")
public class EventRestController {
	
	@Autowired
	private EventService eventService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SportService sportService;
	
	public EventRestController(UserService userService, EventService eventService, SportService sportService) {
		this.userService = userService;
		this.eventService = eventService;
		this.sportService = sportService;
	}
	
	@GetMapping("/event/lat={lat}/lon={lon}/u={u}")
	public List<Event> displayAllFutureEventsCloseToUser (@PathVariable("lat") double lat, 
				@PathVariable("lon") double lon, @PathVariable("u") long u) {
		User user = userService.findById(u);
		double radius = user.getRadius();
		
		return eventService.findByUserDistanceAndCurrentTime(lat, lon, radius);
	}
	
	@GetMapping("/event/{id}")
	public Event displayEventData(@PathVariable("id") long id) {
		return eventService.findById(id);
	}

}
