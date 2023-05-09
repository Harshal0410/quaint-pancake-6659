package com.company.main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.company.entities.Address;
import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.CourseException;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;
import com.company.services.AdminRoleImpl;
import com.company.services.StudentRoleImpl;
import com.company.utilities.Admin;
import com.company.utilities.FileExists;

public class Main {
	
	public static void adminFunctionality(Scanner sc,Map<String, Student> students,Map<String, Course> courses,Map<String, Student> register) throws InvalidDetailsException {
		
		adminLogin(sc);
		AdminRoleImpl a = new AdminRoleImpl();
		StudentRoleImpl sr = new StudentRoleImpl();
		
		int choice = 0;
		try {
			do {
				System.out.println("Press 1 add the course");
				System.out.println("Press 2 view all the students");
				System.out.println("Press 3 to see student course wise");
				System.out.println("Press 4 to delete student information");
				System.out.println("Press 5 view all courses");
				System.out.println("Press 6 to log out");
				choice = sc.nextInt();
				
				
				switch(choice) {
				
				case 1:
					adminAddCourse(sc,a,courses);
					break;
					
				case 2:
					a.studentDetails(students);
					break;
				
				case 3:
					System.out.println("Please enter the course name");
					String courseName = sc.next();
					a.studentBatchwise(register, courseName);
					break;
					
				case 4:
					adminDeleteStudent(sc,a,students);
					break;
				case 5:
					List<Course> list = sr.viewAllCourses(courses);
					System.out.println(list);
					break;
				case 6:
					System.out.println("Logout successful");
					break;
				default:
					System.out.println("Invalid choice");
				}
			}
			while(choice<=5);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void adminDeleteStudent(Scanner sc,AdminRoleImpl a,Map<String, Student> students) {
		
		System.out.println("Please enter the email of student");
		String email = sc.next();
		
		try {
			a.deleteStudent(students, email);
		} catch (InvalidDetailsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void adminAddCourse(Scanner sc,AdminRoleImpl a,Map<String, Course> courses) {
		
		System.out.println("Please enter the name of the course");
		String courseName = sc.next();
		System.out.println("Please enter the number of seats available");
		int seats = sc.nextInt();
		System.out.println("Please enter the start date in (dd/MM/yyyy) format");
		String date1 = sc.next();
		System.out.println("Please enter the end date in (dd/MM/yyyy) format");
		String date2 = sc.next();
		
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate start = LocalDate.parse(date1, format);
		LocalDate end = LocalDate.parse(date2, format);
		
		try {
			a.addCourse(courses, courseName, seats, start, end);
		} catch (DuplicateDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void adminLogin(Scanner sc) throws InvalidDetailsException{
		
		System.out.println("Please enter the username");
		String userName = sc.next();
		System.out.println("Please enter the password");
		String pass = sc.next();
		
		if(userName.equals(Admin.username)) {
			if(pass.equals(Admin.password)) {
				System.out.println("Login successful");
			}
			else {
				throw new InvalidDetailsException("Enter the correct password");
			}
		}
		else {
			throw new InvalidDetailsException("No such user found!");
		}
		
	}
	
	
	public static void studentFunctionality(Scanner sc,Map<String, Student> students,Map<String, Course> courses,Map<String, Student> register) throws InvalidDetailsException {
		
		StudentRoleImpl sr = new StudentRoleImpl();
		
		System.out.println("Please enter the following details to logIn");
		System.out.println("Please enter the email");
		String email = sc.next();
		System.out.println("Please enter the password");
		String pass = sc.next();
		sr.login(email, pass, students);
		System.out.println("login successfull");
		
		try {
			int choice = 0;
			do {
				System.out.println("Select the option of your choice");
				System.out.println("Press 1 to view all courses");
				System.out.println("Press 2 to register in a course");
				System.out.println("Press 3 to update password");
				System.out.println("Press 4 to logout");
				choice = sc.nextInt();
				
				switch(choice) {
					
				case 1:
					List<Course> list = sr.viewAllCourses(courses);
					System.out.println(list);
					break;
				
				case 2:
					System.out.println("Please enter the course name");
					String courseName = sc.next();
					System.out.println("Please enter the email");
					String e = sc.next();
					
					courseRegistration(sr,courses,courseName,e,register,students);
					break;
				
				case 3:
					updatePassword(sc,students,sr);
					break;
					
				case 4:
					System.out.println("Logout successful");
					break;
				default :
					System.out.println("Invald choice");
					break;
				}
				
			}
			while(choice <= 3);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void updatePassword(Scanner sc,Map<String, Student> students,StudentRoleImpl sr) {
		
		System.out.println("Please enter your email id");
		String email = sc.next();
		System.out.println("Please enter the old password");
		String oldPass = sc.next();
		System.out.println("Please enter the new password");
		String newPass = sc.next();
		
		try {
			sr.changePassword(students, email, oldPass, newPass);
		} catch (InvalidDetailsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void courseRegistration(StudentRoleImpl sr,Map<String, Course> courses,String courseName,String email,Map<String, Student> register,Map<String, Student> students) {
		try {
			sr.register(courses, courseName, email, register, students);
		} catch (InvalidDetailsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Course> viewAllCourese(Map<String, Course> courses){ 
		StudentRoleImpl s1 = new StudentRoleImpl();
		
		List<Course> list = null;
		try {
			list = s1.viewAllCourses(courses);
		} catch (CourseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	public static void studentSignup(Scanner sc, Map<String, Student> students) throws DuplicateDataException {
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
		studentInfo.signUp(cus, students);
		System.out.println("Student details added successfully");

	}
	
	
	public static void main(String[] args) throws ParseException, FileNotFoundException, IOException, ClassNotFoundException, DuplicateDataException, InvalidDetailsException {
		
		Scanner sc = new Scanner(System.in);
		
		Map<String, Course> courses = FileExists.courseFile();
		Map<String, Student> students = FileExists.studentFile();
		Map<String, Student> register = FileExists.registerFile();
		
		System.out.println("Welcome to student registration system");
		
		try {
			int choice = 0;
			do {
				System.out.println("Press 1 if admin");
				System.out.println("Press 2 for student login");
				System.out.println("Press 3 for student registration");
				System.out.println("Press 0 to exit the system");
				choice = sc.nextInt();
				
				switch(choice) {
				
				case 1:
					adminFunctionality(sc,students,courses,register);
					break;
					
				case 2:
					studentFunctionality(sc,students,courses,register);
					break;
					
				case 3:
					studentSignup(sc,students);
					break;
				case 0:
					System.out.println("Logout from system successful");
					break;
				default:
					System.out.println("Invalid choice");
					break;
				}
			}
			while(choice != 0);
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		finally {
			try {
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("Courses.ser"));
				coos.writeObject(courses);
				
				ObjectOutputStream soos = new ObjectOutputStream(new FileOutputStream("Students.ser"));
				soos.writeObject(students);
				
				ObjectOutputStream roos = new ObjectOutputStream(new FileOutputStream("Register.ser"));
				roos.writeObject(register);
				
				coos.flush();
				soos.flush();
				roos.flush();
			}
			catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}	
	}
}
