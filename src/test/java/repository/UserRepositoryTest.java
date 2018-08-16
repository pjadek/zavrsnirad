package repository;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.SportifyApplication;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class UserRepositoryTest {
	
	@Autowired
	private TestEntityManager entityManager;
	
  	@Autowired
  	private UserRepository userRepository;

	@Test
	public void whenUsernameIsPresentListIsNotNull() throws Exception {
		List<User> userList = userRepository.findIdByUsername("pjadek");

		assertNotNull(userList);
	}
	
	@Test
	public void shouldListNoUserIfRepositoryIsEmpty() {
		Iterable<User> user = userRepository.findAll();
		assertThat(user).isEmpty();
	}
	
	@Test
	public void shouldSaveUser() {
		User user = userRepository.save(
				new User(
						1, "pjadek", "123", "jadek.petar@gmail.com", "Petar", "Jadek", "1996-12-22", "NULL", 8
						)
				);
		
		assertThat(user).isNotNull();
		assertEquals("pjadek", user.getUsername());
	}
	
	@Test
	public void shouldFindUserByID() {
		User user = userRepository.save(
				new User(
						1, "pjadek", "123", "jadek.petar@gmail.com", "Petar", "Jadek", "1996-12-22", "NULL", 8
						)
				);
		entityManager.persist(user);
		
		User foundUser = userRepository.findOne(user.getId());
		assertThat(foundUser).isEqualTo(user);
	}

}