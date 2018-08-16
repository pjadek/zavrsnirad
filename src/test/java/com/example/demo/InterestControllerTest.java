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

import com.example.demo.model.Interest;
import com.example.demo.model.InterestId;
import com.example.demo.resource.InterestRestController;
import com.example.demo.service.InterestService;

@RunWith(SpringRunner.class)
@WebMvcTest(InterestRestController.class)
@AutoConfigureMockMvc(secure = false)
public class InterestControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private InterestService interestService;
	
	InterestId iId;
	Interest interest;
	List<Interest> interestList;
	
	@Before
	public void setup() {
		iId = new InterestId(1, 1);
		interest = new Interest(iId);
	}
	
	@Test
	public void whenSelectedUserIdShowAllInterests() throws Exception {
		assertThat(this.interestService).isNotNull();
		when(interestService.findByUser((long) 1)).thenReturn(interestList);
		
		mockMvc.perform(get("/api/rest/interest/user/{id}", 1))
			.andExpect(status().isOk())
			.andDo(print())
			.andReturn();
		
	}

}
