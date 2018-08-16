package resource;


import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.demo.resource.LoginRegistrationController;

@RunWith(SpringRunner.class)
public class LoginRegistrationControllerTest {

	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("WEB-INF/web-templates/login");
		viewResolver.setSuffix(".html");
		
		mockMvc = MockMvcBuilders.standaloneSetup(new LoginRegistrationController())
				.setViewResolvers(viewResolver)
				.build();
	}
	
	@Test
	public final void setupLoginPageShouldReturnLoginPage() throws Exception {
		this.mockMvc.perform(get("/login"))
					.andExpect(status().isOk())
					.andExpect(view().name("login"))
					.andDo(print());
	}
	
	@Test
	public final void setupRegisterPageShouldReturnRegisterPage() throws Exception {
		this.mockMvc.perform(get("/register"))
					.andExpect(status().isOk())
					.andExpect(view().name("register"))
					.andDo(print());
	}

}