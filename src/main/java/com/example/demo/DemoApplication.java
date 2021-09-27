package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(StudentRepository repository
	, MongoTemplate mongoTemplate) {
		return  args -> {
			Address address =new Address(
					"Sri Lanka",
					"Avissawella",
					"123"
			);
			String email ="supunlakmal61@gmail.com";
			Student student = new Student(
					"Supun",
					"Lakmal",
					email,
					Gender.MALE,
					address,
					BigDecimal.TEN,
					LocalDateTime.now()
			);


	usingMongoTemplateAndQuery(repository, mongoTemplate, email, student);

//			repository.findStudentByEmail(email).orElse(student1 -> {
//				System.out.println(student + "already exists");
//			});

		};
	}

	private void usingMongoTemplateAndQuery(StudentRepository repository, MongoTemplate mongoTemplate, String email, Student student) {
		Query query = new Query();
		query.addCriteria(Criteria.where("email").is(email));

		List<Student> studentList = mongoTemplate.find(query, Student.class);

		if(studentList.size() > 1){
			throw  new IllegalStateException( "Found many students with emil " + email);
		}

		if(studentList.isEmpty()){
			System.out.println(("Add student"+ student));
			repository.insert(student);
		}else {
			System.out.println(student + "already exists");
		}
	}

}
