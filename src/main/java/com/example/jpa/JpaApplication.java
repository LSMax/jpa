package com.example.jpa;

import com.example.jpa.interceptor.AuthenticationInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class JpaApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(JpaApplication.class, args);
	}
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AuthenticationInterceptor()).addPathPatterns("/**");
	}
}
