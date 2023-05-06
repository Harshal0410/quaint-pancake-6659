package com.company.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Scanner;

import com.company.entities.Address;
import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.DuplicateDataException;
import com.company.services.StudentRoleImpl;
import com.company.utilities.FileExists;

public class Main {

	public static void customerSignup(Scanner sc, Map<String, Student> student) throws DuplicateDataException {
		System.out.println("please enter the following details to Signup");
		System.out.println("please enter the first name");
		String firstName = sc.next();
		System.out.println("please enter the last name");
		String lastName = sc.next();

		System.out.println("enter the address");
		System.out.println("enter the city name");
		String city = sc.next();
		System.out.println("please enter the state name");
		String state = sc.next();
		System.out.println("please enter the pincode");
		int pin = sc.nextInt();
		Address address = new Address(city, state, pin);

		System.out.println("please enter the mobile number");
		String mobNo = sc.next();
		System.out.println("Enter the email id");
		String email = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();

		Student cus = new Student(firstName, lastName, address, mobNo, email, pass);

		StudentRoleImpl studentInfo = new StudentRoleImpl();
		studentInfo.signUp(cus, student);
		System.out.println("Student details added successfully");

	}

	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, ClassNotFoundException {

		Map<String, Course> courses = FileExists.courseFile();
		
		
		String sDate1 = "10/10/2001";
		String sDate2 = "10/10/2010";
		String date1 = "10/09/2022";
		String date2 = "10/10/2022";
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate start = LocalDate.parse(sDate1, format);
		LocalDate end = LocalDate.parse(sDate2, format);
		LocalDate start1 = LocalDate.parse(date1, format);
		LocalDate end1 = LocalDate.parse(date2, format);
		
		Course c1 = new Course("JA111", 60, start, end);
		Course c2 = new Course("JS101", 60, start1, end1);
		
//		System.out.println(c);
		
		courses.put(c1.getCourseName(), c1);
		courses.put(c2.getCourseName(), c2);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Course.ser"));
		oos.writeObject(courses);
		
		oos.flush();
		oos.close();
	}
}
