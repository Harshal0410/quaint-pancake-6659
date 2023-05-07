package com.company.services;

import java.time.LocalDate;
import java.util.Map;

import com.company.entities.Course;
import com.company.entities.Student;
import com.company.exceptions.DuplicateDataException;
import com.company.exceptions.InvalidDetailsException;

public class AdminRoleImpl implements AdminRole{

	@Override
	public void addCourse(Map<String, Course> courses,String courseName,int seats,LocalDate start,LocalDate end) throws DuplicateDataException {
		// TODO Auto-generated method stub
		if(courses.containsKey(courseName)) {
			throw new DuplicateDataException("The course already exist!");
		}
		else {
			Course c = new Course(courseName,seats,start,end);
			courses.put(courseName, c);
			System.out.println("Course added successfully");
		}
	}

	@Override
	public void studentDetails(Map<String, Student> students) throws InvalidDetailsException {
		// TODO Auto-generated method stub
		if(students.size() == 0) {
			System.out.println("No students yet...");
		}
		else {
			for(Map.Entry<String ,Student> m : students.entrySet()) {
				System.out.println(m.getValue());
			}
		}
	}

	@Override
	public void studentBatchwise(Map<String, Student> register,String courseName) throws InvalidDetailsException{
		// TODO Auto-generated method stub
		
		if(!register.containsKey(courseName)) {
			throw new InvalidDetailsException("No such course present");
		}
		
		if(register.size() == 0) {
			System.out.println("No students yet...");
		}
		else {
			int count = 0;
			for(Map.Entry<String ,Student> m : register.entrySet()) {			
				if(m.getKey().equals(courseName)) {
					System.out.println(m.getValue());	
					count++;
				}
			}
			if(count == 0) {
				System.out.println("No student has enrolled in this course yet");
			}
		}
	}

}
