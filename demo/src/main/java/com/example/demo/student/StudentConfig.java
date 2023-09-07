package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//Configuration = a configuration file
//bean class
@Configuration
public class StudentConfig {
	
	//method needs access to student repository
	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {Student bob = new Student(
									"bob", "but.bob@email.com", 
									LocalDate.of(1789, Month.MARCH, 14));
						Student barph = new Student(
									"barph", "b.bob@email.com", 
									LocalDate.of(1719, Month.AUGUST, 14));
						Student zap = new Student(
								"Zap brannigan", "best@cptn@omicron.persei8", 
								LocalDate.of(2983, Month.JUNE, 23));
						//to save to databse call repoistory and use saveAll which takes a list
						repository.saveAll(List.of(bob, barph, zap));
					};
	}
	
}

