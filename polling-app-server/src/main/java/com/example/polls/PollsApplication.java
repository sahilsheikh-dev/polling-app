package com.example.polls;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.polls.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class PollsApplication {

	@Value("${pollsapp.machineIp}")
	private String myAppMessage;

	public static void main(String[] args) {
		SpringApplication.run(PollsApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("STATUS_IP" + myAppMessage);
//				registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
				registry.addMapping("/**").allowedOrigins("http://" + myAppMessage + ":8081").allowedMethods("*");
			}
		};
	}

}
