package com.company.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Course implements Serializable{
	
	String courseName;
	int seats;
	LocalDate startDate;
	LocalDate endDate;
	
	public Course(String courseName, int seats, LocalDate startDate, LocalDate endDate) {
		super();
		this.courseName = courseName;
		this.seats = seats;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getSeats() {
		return seats;
	}
	public void setSeats(int seats) {
		this.seats = seats;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Course [courseName=" + courseName + ", seats=" + seats + ", startDate=" + startDate + ", endDate="
				+ endDate + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(courseName, endDate, seats, startDate);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		return Objects.equals(courseName, other.courseName) && Objects.equals(endDate, other.endDate)
				&& seats == other.seats && Objects.equals(startDate, other.startDate);
	}
	
	
}
