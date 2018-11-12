package com.fbl.work.dao;

import java.util.List;

import com.fbl.work.domain.Enrollment;

public interface RosterDAO {
	
		void insertEnrollment(Enrollment enrollment);
		void insertEnrollment(List<Enrollment> enrollments);
		List<Enrollment> getAllEnrollment();
		void getEnrollmentCountByTeacher();
		void deleteEnrollment(Enrollment enrollment);
		void insertRecord(List<Enrollment> enrollments);
		boolean isExistTeacher(int teacherID);
		boolean isExistSchool(String schoolName);
}
