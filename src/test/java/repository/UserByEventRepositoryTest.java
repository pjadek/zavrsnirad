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
import com.example.demo.model.UserByEvent;
import com.example.demo.model.UserByEventId;
import com.example.demo.repository.UserByEventRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class UserByEventRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private UserByEventRepository userByEventRepository;
	
	@Test
	public void shouldInsertNewUserToEvent() throws Exception {
		UserByEventId id = new UserByEventId(1L, 4L);
		
		UserByEvent userByEvent = userByEventRepository.save(new UserByEvent(id, 0, 0));
		
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
	}
	
	@Test
	public void shoudApproveUserToEvent() throws Exception {
		UserByEventId id = new UserByEventId(1L, 4L);
		UserByEvent userByEvent = userByEventRepository.save(new UserByEvent(id, 0, 0));
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
		
		userByEvent.setApproved(1);
		userByEvent = userByEventRepository.save(userByEvent);
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
		assertEquals(1, userByEvent.getApproved());
	}
	
	@Test
	public void shoudDeclineUserToEvent() throws Exception {
		UserByEventId id = new UserByEventId(1L, 4L);
		UserByEvent userByEvent = userByEventRepository.save(new UserByEvent(id, 0, 0));
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
		
		userByEventRepository.delete(userByEvent);
		testEntityManager.detach(userByEvent);
		testEntityManager.clear();
		
		assertEquals(0, userByEvent.getApproved());
	}
	
	@Test
	public void shouldSetScoreToUser() throws Exception {
		UserByEventId id = new UserByEventId(1L, 4L);
		UserByEvent userByEvent = userByEventRepository.save(new UserByEvent(id, 0, 1));
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
		
		userByEvent.setScore(4);
		userByEvent = userByEventRepository.save(userByEvent);
		testEntityManager.persist(userByEvent);
		
		assertThat(userByEvent).isNotNull();
		assertEquals(4, userByEvent.getScore());
	}
	
	@Test
	public void shoudReturnAverageScoreForUser() throws Exception {
		UserByEventId id_1 = new UserByEventId(1L, 4L);
		UserByEventId id_2 = new UserByEventId(1L, 3L);
		UserByEventId id_3 = new UserByEventId(1L, 2L);
		
		UserByEvent userByEvent_1 = userByEventRepository.save(new UserByEvent(id_1, 4, 1));
		UserByEvent userByEvent_2 = userByEventRepository.save(new UserByEvent(id_2, 5, 1));
		UserByEvent userByEvent_3 = userByEventRepository.save(new UserByEvent(id_3, 5, 1));
		float score = userByEventRepository.findAveargeScoreByUserByEventIdUser(1);
		
		testEntityManager.persist(userByEvent_1);
		testEntityManager.persist(userByEvent_2);
		testEntityManager.persist(userByEvent_3);
		
		assertThat(score).isNotNull();
		assertEquals(14/3, score, 0.2);
	}
	
	@Test
	public void shouldReturnNumberOfAllUsersApprovedForEvent() throws Exception {
		UserByEventId id_1 = new UserByEventId(1L, 4L);
		UserByEventId id_2 = new UserByEventId(2L, 4L);
		UserByEventId id_3 = new UserByEventId(3L, 4L);
		
		UserByEvent userByEvent_1 = userByEventRepository.save(new UserByEvent(id_1, 0, 1));
		UserByEvent userByEvent_2 = userByEventRepository.save(new UserByEvent(id_2, 0, 0));
		UserByEvent userByEvent_3 = userByEventRepository.save(new UserByEvent(id_3, 0, 1));
		
		testEntityManager.persist(userByEvent_1);
		testEntityManager.persist(userByEvent_2);
		testEntityManager.persist(userByEvent_3);
		
		int numberOfUsers = userByEventRepository.findAllUsersApprovedForEvent(4L);
		assertThat(numberOfUsers).isNotNull();
		assertEquals(2, numberOfUsers);
	}
	
	@Test
	public void shouldCheckIfUserIsApprovedForEventAndReturnTrue() throws Exception {
		UserByEventId id_1 = new UserByEventId(1L, 4L);
		UserByEvent userByEvent_1 = userByEventRepository.save(new UserByEvent(id_1, 0, 1));
		testEntityManager.persist(userByEvent_1);
		
		List<UserByEvent> userExists = userByEventRepository.checkIfUserIsApprovedForEvent(4);
		assertThat(userExists).isNotNull();
	}

}