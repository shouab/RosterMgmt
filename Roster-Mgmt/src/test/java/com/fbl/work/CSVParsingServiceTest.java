package com.fbl.work;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fbl.work.util.CSVParsingService;
import com.fbl.work.domain.*;
@ExtendWith(MockitoExtension.class)
@DisplayName("CSV Parsing Service Test")
public class CSVParsingServiceTest {
    @InjectMocks
    private CSVParsingService service;
    @BeforeEach
    void setMockOutput() {
    }
    @Test
    @DisplayName("Mock the output of the CSVParsing Service")
    public void testReadCSVSenario1() throws IOException, ParseException {
    	List<Enrollment> enrollments = new ArrayList<>();
    	enrollments.add(new Enrollment());
    	String BASE_LOCATION = "1.csv";
    	enrollments = service.readCSV(BASE_LOCATION);
    	assertNotNull(enrollments);
    	assertTrue(enrollments.size()>10);
    }
    
    @Test
    @DisplayName("Mock the output of the CSVParsing Service")
    public void testReadCSVSenario2() throws IOException, ParseException {
    	List<Enrollment> enrollments = new ArrayList<>();
    	enrollments.add(new Enrollment());
    	String BASE_LOCATION = "2.csv";
    	enrollments = service.readCSV(BASE_LOCATION);
    	assertNotNull(enrollments);
    	assertTrue(enrollments.size()==0);
    }
}