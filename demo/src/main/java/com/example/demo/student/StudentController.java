package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
//rest controller allows the following class to server restful endpoints
@RestController
//a path can be added to the request map so localhost:8080/api/v1/student
@RequestMapping(path = "api/v1/student")
public class StudentController {
	
	//getMapping allows a get endpoint
	/* @GetMapping	
	 * public String hello() { return "Springs go boing"; }
	 */
	
	/*
	 * dependency injection
	 * here tell variable StudentService to pass an object into the constructer student service
	 * @Autowired will do the job of injecting
	 * however the class studentService needs to be an instantiated class or a spring bean class
	 * this can be done with @Component or @Service
	 */
	private final StudentService studentService;
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	//we can get a json by formatting a list with list.of
	@GetMapping
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	//post is when we want to add new resourceses to the sysem
	//@PostMapping makes sure its a post request
	@PostMapping
	//@RequestBody takes data sent from client request (usually a json) and maps it to student object
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	//maps to path as well as path variable in this case {studentId} is variable
	@DeleteMapping(path = "{studentId}")
	//@PathVariable pulls the variable from the path
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
	//student name and email are request parameters that arnt required then they are passed to studentService
	@PutMapping("{studentId}")
	public void updateStudent(@PathVariable("studentId") Long studentId, 
			@RequestParam(required = false) String name, @RequestParam(required = false) String email) {
		studentService.updateStudent(studentId, name, email);
		
		
	}
}








