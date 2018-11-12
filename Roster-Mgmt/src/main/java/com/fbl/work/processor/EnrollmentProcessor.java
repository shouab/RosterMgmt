package com.fbl.work.processor;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fbl.work.dao.RosterDAO;
import com.fbl.work.domain.Enrollment;
import com.fbl.work.util.CSVParsingService;

@Service
public class EnrollmentProcessor {
	
	@Autowired
	CSVParsingService csvParsingService;
	@Autowired
	RosterDAO rosterDAO;
	
	public void processEnrollment(String location) throws NumberFormatException, IOException, ParseException
	{
		// Read CSV
		List<Enrollment> enrollments = csvParsingService.readCSV(location);
		// Persist Into DB
		rosterDAO.insertRecord(enrollments);
		//Generate Report
		rosterDAO.getEnrollmentCountByTeacher();
		
	}
}
