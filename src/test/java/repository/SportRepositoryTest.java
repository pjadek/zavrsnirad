package repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.SportifyApplication;
import com.example.demo.model.Sport;
import com.example.demo.repository.SportRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = SportifyApplication.class)
public class SportRepositoryTest {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private SportRepository sportRepository;
	
	@Test
	public void shouldListAllSports() {
		Sport sport1 = sportRepository.save(new Sport(1, "Football", "NULL"));
		Sport sport2 = sportRepository.save(new Sport(2, "Handball", "NULL"));
		
		testEntityManager.persist(sport1);
		testEntityManager.persist(sport2);
		
		List<Sport> sportList = sportRepository.findAll();
		
		assertThat(sportList).isNotEmpty();
		assertThat(sportList).hasSize(2);
		assertEquals(sportList.get(0), sport1);
		assertEquals(sportList.get(1), sport2);
	}
	
	@Test 
	public void shouldFindOneSport() {
		Sport sport = sportRepository.save(new Sport(1, "Football", "NULL"));
		testEntityManager.persist(sport);
		
		Sport sportToFind = sportRepository.findOne(sport.getId());
		
		assertThat(sportToFind).isEqualTo(sport);
	}

}
