package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.model.Chat;
import com.example.demo.repository.ChatRepository;

@Service
@Component
public class ChatService {

	@Autowired
	private ChatRepository chatRepository;
	
	public List<Chat> findByEventId(long id) {
		return chatRepository.findByEventId(id);
	}
	
	public void save(Chat chat) {
		chatRepository.save(chat);
	}
}
