package com.example.demo.student;

import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


//to declare an entity to the db use @Entity and @Table table is to create this table in the db
@Entity
@Table
public class Student {
	//creates an id
	@Id
	//will create numerical sequences like auto generate for id
	@SequenceGenerator(name = "student_sequence",
					   sequenceName = "student_sequence",
					   allocationSize = 1)
	//here use the sequenceGenerator to generate the id values using student_sequence
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE,
			generator = "student_sequence")
	private long id;
	private String name;
	private String email;
	private LocalDate dob;
	//@Transient means it doesnt need to be a column we input for the database
	@Transient
	private Integer age;
	
	public Student() {
		
	}
	
	
	
	public Student(String name, String email, LocalDate dob) {
		super();
		this.name = name;
		this.email = email;
		this.dob = dob;
	}



	public Student(long id, String name, String email, LocalDate dob) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.dob = dob;
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public LocalDate getDob() {
		return dob;
	}



	public void setDob(LocalDate dob) {
		this.dob = dob;
	}



	public Integer getAge() {
		return Period.between(this.dob, LocalDate.now()).getYears();
	}



	public void setAge(Integer age) {
		this.age = age;
	}



	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", email=" + email + ", dob=" + dob + ", age=" + age + "]";
	}
	
	
	
	


}
