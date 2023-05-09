package com.company.services;

import java.util.List;
import java.util.Map;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.CourseException;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;

public interface StudentRoles {
	
	public boolean login(String email,String password, Map<String, Student> students) throws InvalidDetailsException;

	public void signUp(Student st, Map<String, Student> students) throws DuplicateDataException;
	
	public List<Course> viewAllCourses(Map<String, Course> courses) throws CourseException;
	
	public void register(Map<String, Course> courses, String name, String email, Map<String,Student> register,Map<String,Student> students) throws InvalidDetailsException;
	
	public void changePassword(Map<String, Student> students,String email,String oldPass,String newPass) throws InvalidDetailsException;
}
