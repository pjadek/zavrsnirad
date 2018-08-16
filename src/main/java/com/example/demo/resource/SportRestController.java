package com.example.demo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sport;
import com.example.demo.service.SportService;

@RequestMapping("/api/rest")
@RestController
public class SportRestController {
	
	@Autowired
	private SportService sportService;
	
	public SportRestController(SportService sportService) {
		this.sportService = sportService;
	}
	
	@GetMapping("/sport/all")
	public List<Sport> listAllSports() {
		return sportService.getAllSports();
	}
	
	@GetMapping("/sport/{id}")
	public Sport pushSportData(@PathVariable("id") long id) {
		return sportService.findSportById(id);
	}

}
