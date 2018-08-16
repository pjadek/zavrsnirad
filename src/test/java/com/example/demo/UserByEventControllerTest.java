package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.model.UserByEvent;
import com.example.demo.model.UserByEventId;
import com.example.demo.resource.UserByEventRestController;
import com.example.demo.service.UserByEventService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserByEventRestController.class)
@AutoConfigureMockMvc(secure = false)
public class UserByEventControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserByEventService userByEventService;
	
	UserByEventId id_1;
	UserByEventId id_2;
	UserByEventId id_3;
	UserByEventId id_4;
	UserByEvent userByEvent_1;
	UserByEvent userByEvent_2;
	UserByEvent userByEvent_3;
	UserByEvent userByEvent_4;
	UserByEvent uEvent;
	
	List<UserByEvent> listOfUsers;
	float score;
	
	@Before
	public void setup() {
		id_1 = new UserByEventId(1L, 2L);
		id_2 = new UserByEventId(2L, 2L);
		id_3 = new UserByEventId(1L, 4L);
		id_4 = new UserByEventId(2L, 4L);
		
		userByEvent_1 = new UserByEvent(id_1, 0, 0);
		userByEvent_2 = new UserByEvent(id_1, 0, 0);
		userByEvent_3 = new UserByEvent(id_3, 5, 1);
		userByEvent_4 = new UserByEvent(id_4, 0, 1);
	}
	
	@Test
	public void shouldListAllApprovedUsers() throws Exception {
		assertThat(this.userByEventService).isNotNull();
		when(userByEventService.findAllByEventAndApproved(4L, 1)).thenReturn(listOfUsers);
		
		mockMvc.perform(get("/api/rest/user/event/{id}/{approved}", 4, 1))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
	}
	
	@Test
	public void shouldListAllPendingUsers() throws Exception {
		assertThat(this.userByEventService).isNotNull();
		when(userByEventService.findAllByEventAndApproved(2L, 0)).thenReturn(listOfUsers);
		
		mockMvc.perform(get("/api/rest/user/event/{id}/{approved}", 2, 0))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
	}
	
	@Test
	public void shouldShowUsersScore() throws Exception {
		assertThat(this.userByEventService).isNotNull();
		when(userByEventService.findAveargeScoreByUserByEventIdUser(1)).thenReturn(score);
		
		mockMvc.perform(get("/api/rest/user/event/{user}/score", 1))
		.andExpect(status().isOk())
		.andDo(print())
		.andReturn();
	}
	
	@Test
	public void shouldSaveUserByEvent() throws Exception {
		assertThat(this.userByEventService).isNotNull();
		String json = userByEvent_1.toString();
		
		mockMvc.perform(post("/user/event/apply")
				.contentType(MediaType.APPLICATION_JSON)
				.content(json)
				.accept(MediaType.APPLICATION_JSON));
	}
	
}