package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Event;

@Repository
public interface EventRepository  extends JpaRepository<Event, Long> {

	@Query(value = "SELECT * FROM event WHERE "
			+ "SQRT(POWER(event.latitude - (:lat), 2) + POWER(event.longitude - (:lon), 2)) <= (:radius) "
			+ "AND CONCAT(event.date, ' ', event.time) >= CURRENT_TIMESTAMP", nativeQuery = true)
	public List<Event> findByUserDistanceAndCurrentTime(@Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radius);
	
	@Query(value = "SELECT * FROM event WHERE "
			+ "CONCAT(event.date, ' ', event.time) >= CURRENT_TIMESTAMP AND organisator = (:user)", nativeQuery = true)
	public List<Event> findMyEvents(@Param("user") long user);
	
	@Query(value = "SELECT * FROM app_events WHERE user = (:user)", nativeQuery = true)
	public List<Event> findMyApprovedEvents(@Param("user") long user);
	
	@Query(value = "SELECT DISTINCT * FROM interest_user_ WHERE user = (:user) AND "
			+ "SQRT(POWER(latitude - (:lat), 2) + POWER(longitude - (:lon), 2)) <= (:radius)", nativeQuery = true)
	public List<Event> findMyInterests(@Param("user") long user, @Param("lat") double lat, @Param("lon") double lon, @Param("radius") double radius);

}
