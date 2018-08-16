package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;

@EnableWebSecurity
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(getPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
		http.authorizeRequests()
			.antMatchers("/api/**").permitAll()
			.antMatchers("/login").permitAll()
			.antMatchers("/app/**").authenticated()
			.and().formLogin().loginPage("/login").failureUrl("/login?error=true")
			.defaultSuccessUrl("/app")
			.usernameParameter("username")
			.passwordParameter("password");
	}
	
	private PasswordEncoder getPasswordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
            	System.out.println(charSequence.toString());
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
            	String pwd = (String)charSequence;
            	if (new String(pwd).equals(s)) {
            	   return true;
            	}
            	else {
               	return false;
               	}
            }
        };
}
	
}
