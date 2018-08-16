package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.Event;
import com.example.demo.model.Sport;
import com.example.demo.model.User;
import com.example.demo.resource.EventRestController;
import com.example.demo.resource.SportRestController;
import com.example.demo.resource.UserRestController;
import com.example.demo.service.EventService;
import com.example.demo.service.SportService;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = {EventRestController.class, SportRestController.class, UserRestController.class} )
@AutoConfigureMockMvc(secure = false)
public class EventControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@MockBean
	private SportService sportService;
	
	@MockBean
	private EventService eventService;
	
	Sport sport;
	User user;
	Event event;
	List<Event> eventList;
	
	@Before
	public void setup() throws Exception {
		Sport sport = new Sport();
		sport.setId((long) 1);
		sport.setName("Football");
		sport.setPictureUrl("NULL");
		
		User user = new User();
		user.setId((long)1);
		user.setUsername("pjadek");
		user.setPassword("123");
		user.setEmail("pjadek@foi.hr");
		user.setFirstName("Petar");
		user.setLastName("Jadek");
		user.setDateOfBirth("1996-12-22");
		user.setPictureUrl("NULL");
		user.setRadius(0.10896);
		
		Event event = new Event();
		event.setId((long) 1);
		event.setName("Football Match");
		event.setDate("2018-20-09");
		event.setTime("20:00:00");
		event.setLatitude(46.3073202);
		event.setLongitude(16.3425919);
		event.setDescription("Description");
		event.setAddress("Address 1 42000 Varazdin");
		event.setMaximumPlayers(22);
		event.setMinimumPlayers(8);
		event.setSport(sport);
		event.setUser(user);
	}
	
	@Test
	public void shouldDisplaySportForUser() throws Exception {
		assertThat(this.eventService).isNotNull();
		assertThat(this.userService).isNotNull();
		assertThat(this.sportService).isNotNull();
		when(eventService.findByUserDistanceAndCurrentTime((double) 46.3094324, (double) 16.3399848, (double) 0.10896)).thenReturn(eventList);
		
		/*
		mockMvc.perform(get("http://localhost:8080/api/rest/event/lat=46.3073202/lon=16.3425919/u=1"))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn(); */
	}

}