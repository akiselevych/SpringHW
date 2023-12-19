package com.toni.homeworkproject;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableTransactionManagement
public class HomeWorkProjectApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(HomeWorkProjectApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("http://localhost:9000/swagger-ui/index.html \n");
	}
}
