package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//@Component tells the system this class is a bean or can use @Service
@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
//	@Autowired will do the job of injecting
	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}
	//JpaRepostory has lots of queries we can send to the database here findAll returns a list of students
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}
	//check if student email exists if it does throw exception otherwise save student
	public void addNewStudent(Student student) {
		Optional<Student> studentOptional =  studentRepository.findStudentByEmail(student.getEmail());
		if(studentOptional.isPresent()) {
			//server.error.include-message=always
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
		
	}
	public void deleteStudent(Long studentId) {
		//finds student by id
		//studentRepository.findById(studentId);
		//finds student by id and returns boolean if exists
		//if not throw exception otherwise delete by id
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("Student with id " + studentId + "does not exist");
			
		}
		studentRepository.deleteById(studentId);
		
		
	}
	//with @Transactional the entity goes into a managed state which means its updated
	//if transaction succeeds
	@Transactional
	public void updateStudent(Long studentId, String name, String email) {
		//find student by id searches for a student if it doesnt find it throw exception () is empty because of void method
		Student student = studentRepository.findById(studentId).orElseThrow(() -> new IllegalStateException(
				"Student with id " + studentId + " does not exist"));
		//checks if name input is empty/> 0 also checks if name doesnt already match existing Object type is used for generic type
		if(name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		//same as name conditions
		if(email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
			//checks if email is already existing with a student using the Optional type and checks if its present
			Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
			if(studentOptional.isPresent()) {
				throw new IllegalStateException("Email taken");
			}
			student.setEmail(email);
		}
		
	}
	
	//saving students

}
