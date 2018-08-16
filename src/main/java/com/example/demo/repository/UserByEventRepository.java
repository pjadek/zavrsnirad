package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserByEvent;
import com.example.demo.model.UserByEventId;

@Repository
public interface UserByEventRepository extends JpaRepository<UserByEvent, UserByEventId> {
	
	@Query(value = "SELECT AVG(score) FROM user_by_event WHERE user = (:user) AND approved > 0 and score > 0", nativeQuery = true)
	float findAveargeScoreByUserByEventIdUser(@Param("user") long user);
	
	@Query(value = "SELECT COUNT(*) FROM user_by_event WHERE event = (:event) AND approved > 0", nativeQuery = true)
	int findAllUsersApprovedForEvent(@Param("event") long event);
	
	@Query(value = "SELECT * FROM user_by_event WHERE event = (:event) AND approved = (:approved)", nativeQuery = true)
	List<UserByEvent> findAllByEventAndApproved(@Param("event") long event, @Param("approved") int approved);
	
	@Query(value = "SELECT * FROM user_by_event WHERE event = (:event)", nativeQuery = true) 
	List<UserByEvent> checkIfUserIsApprovedForEvent(@Param("event") long event);
	
	@Query(value = "SELECT * FROM user_by_event WHERE event = (:event) AND user = (:user)", nativeQuery = true)
	UserByEvent checkUserForEvent(@Param("event") long event, @Param("user") long user);
}
