package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Event;
import com.example.demo.repository.EventRepository;

@Service
@Component
public class EventService {
	
	@Autowired
	private EventRepository eventRepository;

	public List<Event> findByUserDistanceAndCurrentTime(double lat, double lon, double radius) {
		return eventRepository.findByUserDistanceAndCurrentTime(lat, lon, radius);
	}

	public Event findById(long id) {
		return eventRepository.findOne(id);
	}
	
	public List<Event> findMyEvents(long user) {
		return eventRepository.findMyEvents(user);
	}
	
	public List<Event> findMyApprovedEvents(long user) {
		return eventRepository.findMyApprovedEvents(user);
	}
	
	public List<Event> findMyInterests(long user, double lat, double lon, double radius) {
		return eventRepository.findMyInterests(user, lat, lon, radius);
	}
	
}
