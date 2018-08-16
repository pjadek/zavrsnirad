package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Component
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public List<User> findIdByUsername(String username) {
		List<User> userList = userRepository.findIdByUsername(username);
		return userList;
	}
	
	public User findById(long id) {
		return userRepository.findOne(id);
	}
	
	public User findDataByID(String username) {
		return userRepository.findDataByUsername(username);
	}

}
