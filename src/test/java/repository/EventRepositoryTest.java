package repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.SportifyApplication;
import com.example.demo.model.Event;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SportRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class EventRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired 
	private UserRepository userRepository;
	
	@Autowired
	private SportRepository sportRepository;
	
	@Test
	public final void showAllEventsIfTheyDidNotEndedYet() throws Exception {
		Sport sportFootball = sportRepository.save(new Sport(1, "Football", "NULL"));
		testEntityManager.persist(sportFootball);
		
		User user = userRepository.save(new User(1, "pjadek", "123", "pjadek@foi.hr", "Petar", "Jadek", "1996-12-22", "NULL", 0.10896));
		testEntityManager.persist(user);
		
		Event eventFootball = eventRepository.save(new Event(1, "Football 1", "2018-09-20", "19:00:00", 
										16.3421328, 46.3082174, 10, 22, "Desc", "Address", sportFootball, user));
		
		testEntityManager.persist(eventFootball);
		
		List<Event> eventList = eventRepository.findByUserDistanceAndCurrentTime((double) 46.3094324, (double) 16.3399848, (double) 0.10896);
		
		assertThat(eventList).hasSize(1);
		assertEquals(eventList.get(0), eventFootball);
	}
	
	@Test
	public final void shouldCreateEvent() throws Exception {
		Sport sport = sportRepository.save(new Sport(3, "Football", "NULL"));
		testEntityManager.persist(sport);
		
		User user = userRepository.save(new User(2, "pjadek", "123", "pjadek@foi.hr", "Petar", "Jadek", "1996-12-22", "NULL", 0.10896));
		testEntityManager.persist(user);
		
		Event event = eventRepository.save(new Event(4, "Football", "2018-09-20", "19:00:00", 
				16.3421328, 46.3082174, 10, 22, "Desc", "Address", sport, user));
		testEntityManager.persist(event);
		
		assertThat(event).isNotNull();
	}

}
