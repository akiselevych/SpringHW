package com.toni.homeworkproject;

import io.swagger.v3.oas.models.OpenAPI;
import org.apache.commons.lang3.StringUtils;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Comparator;
import java.util.stream.Collectors;

@SpringBootApplication
public class HomeWorkProjectApplication implements ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(HomeWorkProjectApplication.class, args);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("http://localhost:9000/swagger-ui/index.html \n");
	}

	@Bean
	public OpenAPI springShopOpenApi(){
		return new OpenAPI();
	}

}