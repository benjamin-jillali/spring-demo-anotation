package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
//we need to extend jpa repository this is an interface that will connect with the database
//need to have the object and id id is a Long type
//annotate with @Repository this interfacei si responsible for data access
@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	//optional takes value that may or may not be null
	// sql select * from student where email = ...
	//we can write a query like so s is an alias ? is a value insertion
	//this is jpql so student refers to the class entity
	@Query("select s from Student s where s.email = ?1")
	Optional<Student> findStudentByEmail(String email);

} 
