package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDateTime;



@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository) {
		return  args -> {
			Address address =new Address(
					"Sri Lanka",
					"Avissawella",
					"123"
			);
			Student student = new Student(
					"Supun",
					"Lakmal",
					"supunlakmal61@gmail.com",
					Gender.MALE,
					address,
					BigDecimal.TEN,
					LocalDateTime.now()
			);

			repository.insert(student);
		};
	}

}
