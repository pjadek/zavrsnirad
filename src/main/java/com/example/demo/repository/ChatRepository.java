package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Chat;

public interface ChatRepository extends JpaRepository<Chat, Long> {
	
	@Query(value = "SELECT * FROM chat WHERE event = (:event) ORDER BY date_time DESC", nativeQuery = true)
	public List<Chat> findByEventId(@Param("event") long event);

}
