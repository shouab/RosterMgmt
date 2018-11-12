package com.fbl.work.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fbl.work.constants.Action;
import com.fbl.work.domain.Enrollment;
import com.fbl.work.domain.TeacherCountReport;

@Repository
public class RosterDAOImpl extends JdbcDaoSupport implements RosterDAO {

	@Autowired
	DataSource ds;
	private static final Logger logger = LoggerFactory.getLogger(RosterDAOImpl.class);
	private static final String isExistTeacher = "SELECT count(*) FROM teacher WHERE id = ?;";
	private static final String isExistSchool = "SELECT count(*) FROM school WHERE school_name = ?;";
	@PostConstruct
	private void initialize() {
		setDataSource(ds);
	}

	/**
	 * This method will persist the Enrollments Records in the DB and update the existing one
	 * @param enrollments
	 */
	@Transactional
	public void insertRecord(List<Enrollment> enrollments) {
		int deletedCount = 0;
		List<String> queries = new ArrayList<>();
		for (Enrollment enrollment : enrollments) {
			if (enrollment.getAction() == null || !enrollment.getAction().getActionCode().equalsIgnoreCase(Action.DELETE.getActionCode())) {
				queries.clear();
				if (isExistTeacher(enrollment.getTeacher().getId())) {
					String sqlTeacher = "INSERT INTO teacher " + "(id, first_name, last_name, email) VALUES ("
							+ enrollment.getTeacher().getId() + ",'" + enrollment.getTeacher().getFirstName() + "','"
							+ enrollment.getTeacher().getLastName() + "','" + enrollment.getTeacher().getEmail() + "')";
					queries.add(sqlTeacher);
				} else {
					String sqlTeacherUpdate = "Update teacher" + " set first_name = '"
							+ enrollment.getTeacher().getFirstName() + "',last_name = '"
							+ enrollment.getTeacher().getLastName() + "',email = '" + enrollment.getTeacher().getEmail()
							+ "' where id = " + enrollment.getTeacher().getId();
					queries.add(sqlTeacherUpdate);
				}
				if (isExistSchool(enrollment.getSchool().getSchoolName())) {
					String sqlSchool = "INSERT INTO school " + "(school_name, school_district,school_state) VALUES ('"
							+ enrollment.getSchool().getSchoolName() + "','" + enrollment.getSchool().getDistrictName()
							+ "','" + enrollment.getSchool().getState() + "')";
					queries.add(sqlSchool);
				} else {
					String sqlSchoolUpdate = "Update school" + " set school_district = '"
							+ enrollment.getSchool().getDistrictName() + "',school_state = '"
							+ enrollment.getSchool().getState() + "' where school_name = " + "'"
							+ enrollment.getSchool().getSchoolName() + "'";
					queries.add(sqlSchoolUpdate);
				}
				String sqlEnrollment = "INSERT INTO enrollment "
						+ "(student_id, teacher_id,grade,course,section) VALUES (" + enrollment.getStudent().getId()
						+ "," + enrollment.getTeacher().getId() + ",'" + enrollment.getGrade() + "','"
						+ enrollment.getCourse() + "','" + enrollment.getSection() + "')";

				String sqlStudent = "INSERT INTO student "
						+ "(id, state_id, first_name, last_name, gender, birthdate, race, meal_status, english_proficiency, native_language, "
						+ " service_code, primary_disability_type, iep_reading, iep_math, iep_behavior, gifted_talented, section504, mobility) "
						+ " VALUES (" + enrollment.getStudent().getId() + ",'"
						+ enrollment.getStudent().getStudentStateId() + "','" + enrollment.getStudent().getFirstName()
						+ "','" + enrollment.getStudent().getLastName() + "','" + enrollment.getStudent().getGender()
						+ "','" + new java.sql.Date(enrollment.getStudent().getDateOfBirth().getTime()) + "','"
						+ enrollment.getStudent().getRace() + "','" + enrollment.getStudent().getMealStatus() + "','"
						+ enrollment.getStudent().getEngProficiency() + "','"
						+ enrollment.getStudent().getNativeLanguage() + "','" + enrollment.getStudent().getServiceCode()
						+ "','" + enrollment.getStudent().getDisabilityType() + "','"
						+ enrollment.getStudent().getIepReading() + "','" + enrollment.getStudent().getIepMath() + "','"
						+ enrollment.getStudent().getIepBehavior() + "','" + enrollment.getStudent().getGifted() + "','"
						+ enrollment.getStudent().getSection504() + "','" + enrollment.getStudent().getMobility()
						+ "')";
				queries.add(sqlStudent);
				queries.add(sqlEnrollment);
				String[] queriesArray = new String[queries.size()];
				getJdbcTemplate().batchUpdate(queries.toArray(queriesArray));
			}
			else
			{
				deletedCount++;
				deleteEnrollment(enrollment);
			}
		}
		logger.info("Deleteted Count:" + deletedCount);
	}

	@Override
	public void insertEnrollment(Enrollment enrollment) {
		String sql = "INSERT INTO enrollment " + "(student_id, teacher_id,grade,course,section) VALUES (?, ?)";
		getJdbcTemplate().update(sql,
				new Object[] { enrollment.getStudent().getId(), enrollment.getTeacher().getId(), enrollment.getGrade(),
						enrollment.getCourse(), enrollment.getSection(), enrollment.getSchool().getSchoolName() });
	}

	@Override
	public void insertEnrollment(List<Enrollment> enrollments) {
		// TODO provide implementation

	}
	
	@Override
	public List<Enrollment> getAllEnrollment() {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * This Method will Generate Report For Teacher Allocation 
	 */
	@Override
	public void getEnrollmentCountByTeacher() {
		String sql = "select teacher_id as 'Teacher ID' ,count(student_id) as 'StudentNo',grade as 'Grade' from enrollment\r\n"
				+ "group by teacher_id,grade";
		@SuppressWarnings("unchecked")
		List<TeacherCountReport> report = getJdbcTemplate().query(sql, new TeacherCountRowMapper());
		if (report != null && !report.isEmpty()) {
			logger.info("Teacher ID             Student Count             Grade");
			report.forEach(rpt -> {
				logger.info(rpt.getTeacherId() + "                      " + rpt.getStudentCount()
						+ "                       " + rpt.getGrade());
			});
		}
	}

	@Override
	public void deleteEnrollment(Enrollment enrollment) {
		String deleteEnrollmentSql = "DELETE FROM enrollment WHERE student_id =" + enrollment.getStudent().getId() +" and teacher_id = "+enrollment.getTeacher().getId();
		String deleteStudentSql = "DELETE FROM student WHERE id =" + enrollment.getStudent().getId();
		String[] queries = new String[] { deleteEnrollmentSql, deleteStudentSql };
		getJdbcTemplate().batchUpdate(queries);
	}

	/**
	 * 
	 * @param teacherID
	 * @return
	 */
	@Override
	public boolean isExistTeacher(int teacherID)
	{
		boolean result = false;

		int count = getJdbcTemplate().queryForObject(
	                        isExistTeacher, new Object[] { teacherID }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	/**
	 * 
	 * @param schoolName
	 * @return
	 */
	@Override
	public boolean isExistSchool(String schoolName)
	{
		boolean result = false;

		int count = getJdbcTemplate().queryForObject(
	                        isExistSchool, new Object[] { schoolName }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}
	
	@SuppressWarnings("rawtypes")
	public class TeacherCountRowMapper implements RowMapper {
		public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
			TeacherCountReport report = new TeacherCountReport();
			report.setTeacherId(rs.getInt("Teacher ID"));
			report.setStudentCount(rs.getInt("StudentNo"));
			report.setGrade(rs.getString("Grade"));
			return report;
		}

	}
	
	
}
