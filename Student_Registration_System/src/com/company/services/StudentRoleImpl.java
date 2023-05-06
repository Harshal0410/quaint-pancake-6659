package com.company.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.CourseException;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;

public class StudentRoleImpl implements StudentRoles{

	@Override
	public boolean login(String email, String password, Map<String, Student> students) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		if(students.containsKey(email)) {
			
			if(students.get(email).getPassword().equals(password)) {
				return true;
			}
			else {
				throw new InvalidDetailsException("Invalid password, try again");
			}
		}
		else {
			throw new InvalidDetailsException("You have not sign up yet, please signup");
		}
	}

	@Override
	public void signUp(Student st, Map<String, Student> students) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if(students.containsKey(st.getEmail())) {
			throw new DuplicateDataException("Student is already registered, please logIn.");
		}
		else {
			students.put(st.getEmail(), st);
		}
		
	}

	@Override
	public List<Course> viewAllCourses(Map<String, Course> courses) throws CourseException {
		// TODO Auto-generated method stub
		List<Course> list = null;
		
		if(courses != null && courses.size() > 0) {
			Collection<Course> col = courses.values();
			list = new ArrayList<>(col);
		}
		else {
			throw new CourseException("No course available");
		}
		
		return list;
	}
	
	public void register(Map<String, Course> courses, String name, String email, Map<String,Student> register) throws InvalidDetailsException {
//		if(Course)
	}

}
