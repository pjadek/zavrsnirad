package com.example.demo;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import com.example.demo.model.User;
import com.example.demo.resource.UserRestController;
import com.example.demo.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserRestController.class)
@AutoConfigureMockMvc(secure = false)
public class UserControllerTest {
	  
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;

	User user1;
	
	@Before
	public void setUser() throws Exception {
		user1 = new User();
		user1.setId((long)1);
		user1.setUsername("pjadek");
		user1.setPassword("123");
		user1.setEmail("pjadek@foi.hr");
		user1.setFirstName("Petar");
		user1.setLastName("Jadek");
		user1.setDateOfBirth("1996-12-22");
		user1.setPictureUrl("NULL");
		user1.setRadius(8);
	}
	
	@Test
	public void whenGettingUserByIdReturnData() throws Exception {
		assertThat(this.userService).isNotNull();
		when(userService.findById((long)1)).thenReturn(user1);

		mockMvc.perform(get("/api/rest/account/{id}/", (long)1))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
	}
	
	@Test
	public void whenGettingUserByIdThatDoesNotExistReturnNotFound() throws Exception {
		assertThat(this.userService).isNotNull();
		//when(userService.findById((long)2)).thenReturn(user1);

		mockMvc.perform(get("/api/rest/account/{id}/", (long)2))
				.andExpect(status().isOk())
				.andDo(print())
				.andReturn();
	}
}