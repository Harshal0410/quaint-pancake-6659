package com.company.services;

import java.time.LocalDate;
import java.util.Map;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;

public interface AdminRole {

	public void addCourse(Map<String, Course> courses,String courseName,int seats,LocalDate start,LocalDate end) throws DuplicateDataException;
	
	public void studentDetails(Map<String, Student> students) throws InvalidDetailsException;
	
	public void studentBatchwise(Map<String, Student> register, String courseName) throws InvalidDetailsException;
}
