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
import com.example.demo.model.Interest;
import com.example.demo.model.InterestId;
import com.example.demo.repository.InterestRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class InterestRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private InterestRepository interestRepository;
	
	@Test
	public void showAllInterestsBindedToUser() throws Exception {
		InterestId iId1 = new InterestId(1L, 1L);
		InterestId iId2 = new InterestId(2L, 1L);
		
		Interest interest1 = interestRepository.save(new Interest(iId1));
		Interest interest2 = interestRepository.save(new Interest(iId2));
		
		testEntityManager.persist(interest1);
		testEntityManager.persist(interest2);
		
		List<Interest> interestByUserList = interestRepository.findByInterestIdUser(iId1.getUser());
		
		assertThat(interestByUserList).hasSize(2);
		assertEquals(interestByUserList.get(0), interest1);
		assertEquals(interestByUserList.get(1), interest2);
	}
	
	@Test
	public void updateInterestForUser() throws Exception {
		InterestId iId1 = new InterestId(1L, 1L);
		InterestId iId2 = new InterestId(2L, 1L);
		
		Interest interest1 = interestRepository.save(new Interest(iId1));
		Interest interest2 = interestRepository.save(new Interest(iId2));
		
		iId2.setSport(3L);
		interest2.setInterestId(iId2);
		interest2 = interestRepository.save(interest2);
		
		testEntityManager.persist(interest1);
		testEntityManager.persist(interest2);
		
		List<Interest> interestByUserList = interestRepository.findByInterestIdUser(iId1.getUser());
		
		assertThat(interestByUserList).hasSize(2);
		assertEquals(interestByUserList.get(0), interest1);
	}

}
