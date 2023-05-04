package com.company.services;

import java.util.Map;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.CourseException;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;

public interface StudentRoles {
	
	public boolean login(String email,String password, Map<String, Student> students) throws InvalidDetailsException;

	public void signUp(Student st, Map<String, Student> students) throws DuplicateDataException;
	
	public void viewAllCourses(Map<Integer, Course> courses) throws CourseException;
}
