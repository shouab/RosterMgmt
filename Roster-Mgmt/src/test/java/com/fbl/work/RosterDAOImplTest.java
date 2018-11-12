package com.fbl.work;

import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.fbl.work.dao.RosterDAOImpl;
import com.fbl.work.domain.Enrollment;
import com.fbl.work.domain.School;
import com.fbl.work.domain.Student;
import com.fbl.work.domain.Teacher;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class RosterDAOImplTest {

	@Autowired
	private RosterDAOImpl rosterDAO;

	@Test
	@Transactional
	public void insertRecordTest() {
		Integer Id = 123453;
		Enrollment enrollment = new Enrollment(
				new Student(Id, "S12345", "First", "Last", "M", new Date(), "AS", "Y", "A", "U", "A", "A",
						"Y", "Y", "Y", "Y", "Y", "N"),
				new Teacher(Id, "TFirst", "TLast", "T@tt.com"), "KG", "S1", "SS",
				new School("ISD4", "testschoolName", "MN"));
		List<Enrollment> enrollments = new ArrayList<Enrollment>();
		enrollments.add(enrollment);
		rosterDAO.insertRecord(enrollments);
	}
	
	@Test
	public void isExist() {
		assertFalse(rosterDAO.isExistTeacher(54321));
	}
}
