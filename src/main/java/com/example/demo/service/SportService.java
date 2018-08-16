package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;

@Service
@Component
public class SportService {
	
	@Autowired
	private SportRepository sportRepository;

	public List<Sport> getAllSports() {
		return sportRepository.findAll();
	}

	public Sport findSportById(long id) {
		return sportRepository.findOne(id);
	}

}
