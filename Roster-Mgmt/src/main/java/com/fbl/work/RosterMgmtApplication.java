package com.fbl.work;

import java.io.IOException;
import java.text.ParseException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fbl.work.processor.EnrollmentProcessor;

@SpringBootApplication
public class RosterMgmtApplication {
	@Autowired
	EnrollmentProcessor processor;
	private static final Logger logger = LoggerFactory.getLogger(RosterMgmtApplication.class);
	private static final String SETUP_FILE_LOCATION = "1.csv";
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(RosterMgmtApplication.class, args);
		logger.info("Enrollment Load Started");
		boolean isDelta = false;
		String location = args[0];
		if(location !=null && location.contains("delta"))
		{
			isDelta = true;
		}
		EnrollmentProcessor processor = context.getBean(EnrollmentProcessor.class);
		try {
			if(isDelta)
			{
				processor.processEnrollment(SETUP_FILE_LOCATION);
			}
			processor.processEnrollment(location);
			logger.info("Enrollment Load Ended");
		} catch (NumberFormatException | IOException | ParseException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
