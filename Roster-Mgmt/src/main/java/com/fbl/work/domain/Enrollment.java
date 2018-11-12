package com.fbl.work.domain;

import java.io.Serializable;

import com.fbl.work.constants.Action;

public class Enrollment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Student student;
	private Teacher teacher;
	private String grade;
	private String course;
	private String section;
	private School school;
	private Action action;
	public Enrollment() {
		
	}
	
	public Enrollment(Student student, Teacher teacher, String grade, String course, String section,
			School school)
	{
		this.student = student;
		this.teacher = teacher;
		this.grade = grade;
		this.course = course;
		this.section = section;
		this.school = school;
	}
	
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getCourse() {
		return course;
	}
	public void setCourse(String course) {
		this.course = course;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public School getSchool() {
		return school;
	}
	public void setSchool(School school) {
		this.school = school;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}
	
}
