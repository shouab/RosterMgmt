package com.fbl.work.util;

/**
 * 
 */
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.fbl.work.constants.Action;
import com.fbl.work.domain.Enrollment;
import com.fbl.work.domain.School;
import com.fbl.work.domain.Student;
import com.fbl.work.domain.Teacher;

@Service
public class CSVParsingService {

	// DOB Format
	private final DateFormat dobFormat = new SimpleDateFormat("MM/dd/YY");
	private static final String BASE_LOCATION = "https://github.com/pclarkfbl/fbl-homework/raw/master/";
	private static final Logger logger = LoggerFactory.getLogger(CSVParsingService.class);

	/**
	 * 
	 * @return
	 * @throws IOException
	 * @throws NumberFormatException
	 * @throws ParseException
	 */
	public List<Enrollment> readCSV(String location) throws IOException, ParseException {
		// CSVFormat object
		CSVFormat format = CSVFormat.RFC4180.withHeader().withDelimiter(',');
		URL url = new URL(BASE_LOCATION + location);
		CSVParser parser = CSVParser.parse(url, Charset.defaultCharset(), format);
		List<Enrollment> enrollments = new ArrayList<Enrollment>();
		int rejectCount = 0;
		String studentName = "";

		for (CSVRecord record : parser) {
			try {
				studentName = record.get("StudentFirstName")+ " " +record.get("StudentLastName");
				Teacher teacher = new Teacher(Integer.parseInt(record.get("TeacherID")), record.get("TeacherFirstName"),
						record.get("TeacherLastName"), record.get("TeacherEmail"));
				Student student = new Student(Integer.parseInt(record.get("StudentID")), record.get("StudentStateID"),
						record.get("StudentFirstName"), record.get("StudentLastName"), record.get("StudentGender"),
						dobFormat.parse(record.get("StudentBirthDate")), record.get("StudentRace"),
						record.get("MealStatus"), record.get("EnglishProficiency"), record.get("NativeLanguage"),
						record.get("ServiceCode"), record.get("PrimaryDisabilityType"), record.get("IEPReading"),
						record.get("IEPMath"), record.get("IEPBehavior"), record.get("GiftedAndTalented"),
						record.get("Section504"), record.get("Mobility"));
				
				Enrollment enrollment = new Enrollment(student, teacher, record.get("Grade"), record.get("Course"),
						record.get("Section"), null);
				if (record.isSet("Action")) {
					School school = new School(record.get("SchoolDistrict"), record.get("School"), record.get("State"));
					enrollment.setAction(Action.fromCode(record.get("Action")));
					enrollment.setSchool(school);
				} else {
					School school = new School(record.get("SchoolDistrict"), record.get("School"), record.get(1));
					enrollment.setSchool(school);
				}

				enrollments.add(enrollment);
			} catch (NumberFormatException ne) {
				logger.info("Rejected Record with Student Name:" + studentName +"with Error"+ne.getMessage());
				rejectCount++;
			}
		}
		// close the parser
		parser.close();
		logger.info("Rejected Records Count:" + rejectCount);
		return enrollments;
	}
}
