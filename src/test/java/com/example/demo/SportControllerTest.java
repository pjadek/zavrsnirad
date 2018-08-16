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

import com.example.demo.model.Sport;
import com.example.demo.resource.SportRestController;
import com.example.demo.service.SportService;

@RunWith(SpringRunner.class)
@WebMvcTest(SportRestController.class)
@AutoConfigureMockMvc(secure = false)
public class SportControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private SportService sportService;
	
	Sport sport1;
	Sport sport2;
	List<Sport> sportList;
	
	@Before
	public void setSportList() throws Exception {
		Sport sport1 = new Sport();
		sport1.setId((long) 1);
		sport1.setName("Football");
		sport1.setPictureUrl("NULL");
		
		Sport sport2 = new Sport();
		sport2.setId((long) 2);
		sport2.setName("Handball");
		sport2.setPictureUrl("NULL");
	}
	
	@Test
	public void shouldPushAllSports() throws Exception {
		assertThat(this.sportService).isNotNull();
		when(sportService.getAllSports()).thenReturn(sportList);
		
		mockMvc.perform(get("/api/rest/sport/all"))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
	}
	
	@Test
	public void shouldPushSportById() throws Exception {
		assertThat(this.sportService).isNotNull();
		when(sportService.findSportById((long) 1)).thenReturn(sport1);
		
		mockMvc.perform(get("/api/rest/sport/{id}", 1))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
	}

}
