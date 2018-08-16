package repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.SportifyApplication;
import com.example.demo.model.Chat;
import com.example.demo.model.Event;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.repository.ChatRepository;
import com.example.demo.repository.EventRepository;
import com.example.demo.repository.SportRepository;
import com.example.demo.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class ChatRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ChatRepository chatRepository;
	
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SportRepository sportRepository;
	
	@Test
	public void saveChatMessage() throws Exception {
		Sport sportFootball = sportRepository.save(new Sport(1, "Football", "NULL"));
		testEntityManager.persist(sportFootball);
		
		User user = userRepository.save(new User(1, "pjadek", "123", "pjadek@foi.hr", "Petar", "Jadek", "1996-12-22", "NULL", 0.10896));
		testEntityManager.persist(user);
		
		Event eventFootball = eventRepository.save(new Event(1, "Football 1", "2018-09-20", "19:00:00", 
				16.3421328, 46.3082174, 10, 22, "Desc", "Address", sportFootball, user));
		testEntityManager.persist(eventFootball);
		
		Chat chat = chatRepository.save(new Chat(1, eventFootball, user, "Hello world!", "2018-07-31 21:51:25"));
		testEntityManager.persist(chat);
		
		assertThat(chat).isNotNull();
	}
	
	@Test
	public void listChatMessagesByEvent() throws Exception {
		Sport sportFootball = sportRepository.save(new Sport(1, "Football", "NULL"));
		testEntityManager.persist(sportFootball);
		
		User user = userRepository.save(new User(1, "pjadek", "123", "pjadek@foi.hr", "Petar", "Jadek", "1996-12-22", "NULL", 0.10896));
		testEntityManager.persist(user);
		
		Event eventFootball = eventRepository.save(new Event(1, "Football 1", "2018-09-20", "19:00:00", 
				16.3421328, 46.3082174, 10, 22, "Desc", "Address", sportFootball, user));
		testEntityManager.persist(eventFootball);
		
		Chat chat_1 = chatRepository.save(new Chat(1, eventFootball, user, "Hello world!", "2018-07-31 21:51:25"));
		Chat chat_2 = chatRepository.save(new Chat(2, eventFootball, user, "This is info!", "2018-07-31 21:51:25"));
		testEntityManager.persist(chat_1);
		testEntityManager.persist(chat_2);
		
		List<Chat> chatList = chatRepository.findByEventId(eventFootball.getId());
		
		assertThat(chatList).hasSize(2);
	}

}
