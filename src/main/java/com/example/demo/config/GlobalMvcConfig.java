package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GlobalMvcConfig extends WebMvcConfigurerAdapter {

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
        //registry.addViewController("/app").setViewName("index");
        registry.addViewController("/").setViewName("/login");
    }
}
