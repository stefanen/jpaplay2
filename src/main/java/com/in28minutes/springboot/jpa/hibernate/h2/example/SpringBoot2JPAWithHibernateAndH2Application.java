package com.in28minutes.springboot.jpa.hibernate.h2.example;

import com.in28minutes.springboot.jpa.hibernate.h2.example.student.StudentChild;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.springboot.jpa.hibernate.h2.example.student.Student;
import com.in28minutes.springboot.jpa.hibernate.h2.example.student.StudentRepository;

import java.util.Arrays;

@SpringBootApplication
public class SpringBoot2JPAWithHibernateAndH2Application implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	StudentRepository repository;

	public static void main(String[] args) {
		SpringApplication.run(SpringBoot2JPAWithHibernateAndH2Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new Thread(() -> {
			doStuff();
		}).start();

	}



	public void doStuff() {

		//Student student=repository.findById(10001L).get();
		//logger.info("Student id 10001 -> {}", student);
		/*
		Student studentToSave=new Student("John", "A1234657");
		StudentChild child1=new StudentChild(studentToSave, "baby1");
		StudentChild child2=new StudentChild(studentToSave, "baby2");

		studentToSave.setChildList(Arrays.asList(child1,child2));

		logger.info("Inserting -> {}", repository.save(studentToSave));
		*/

		Student sameStudentToSave=new Student(1L,"John", "A1234657");
		StudentChild samechild1=new StudentChild(1L,sameStudentToSave, "baby1");
		StudentChild samechild2=new StudentChild(2L,sameStudentToSave, "baby2_renamed");

		sameStudentToSave.setChildList(Arrays.asList(samechild1,samechild2));


		logger.info("Inserting -> {}", repository.save(sameStudentToSave));


		Student fetchedStudent = repository.findById(1L).get();



		fetchedStudent.setPassportNumber("newpassportA1");
		repository.save(fetchedStudent);




		logger.info("done");
		//logger.info("Update 10003 -> {}", repository.save(new Student(10001L, "Name-Updated", "New-Passport")));

		//repository.deleteById(10002L);

		//logger.info("All users -> {}", repository.findAll());
	}
}
