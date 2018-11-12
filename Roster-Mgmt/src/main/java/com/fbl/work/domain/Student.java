package com.fbl.work.domain;

import java.io.Serializable;
import java.util.Date;

public class Student implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String studentStateId;
	private String firstName;
	private String lastName;
	private String gender;
	private Date dateOfBirth;
	private String race;
	private String mealStatus;
	private String engProficiency;
	private String nativeLanguage;
	private String serviceCode;
	private String disabilityType;
	private String iepReading;
	private String iepMath;
	private String iepBehavior;
	private String gifted;
	private String section504;
	private String mobility;
	
	public Student() {
		
	}
	public Student(Integer id, String studentStateId, String firstName, String lastName, String gender,	Date dob, String race, String mealStatus, String engProf, String nativeLanguage, String serviceCode, String disabilityType,
			String iepReading, String iepMath, String iepBehavior, String gifted, String section504, String mobility)
	{
		this.id = id;
		this.studentStateId= studentStateId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dob;
		this.race = race;
		this.mealStatus = mealStatus;
		this.engProficiency = engProf;
		this.nativeLanguage = nativeLanguage;
		this.serviceCode = serviceCode;
		this.disabilityType = disabilityType;
		this.iepMath = iepMath;
		this.iepReading = iepReading;
		this.iepBehavior =  iepBehavior;
		this.gifted = gifted;
		this.section504 = section504;
		this.mobility = mobility;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getStudentStateId() {
		return studentStateId;
	}
	public void setStudentStateId(String studentStateId) {
		this.studentStateId = studentStateId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getRace() {
		return race;
	}
	public void setRace(String race) {
		this.race = race;
	}
	public String getMealStatus() {
		return mealStatus;
	}
	public void setMealStatus(String mealStatus) {
		this.mealStatus = mealStatus;
	}
	public String getEngProficiency() {
		return engProficiency;
	}
	public void setEngProficiency(String engProficiency) {
		this.engProficiency = engProficiency;
	}
	public String getNativeLanguage() {
		return nativeLanguage;
	}
	public void setNativeLanguage(String nativeLanguage) {
		this.nativeLanguage = nativeLanguage;
	}
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getDisabilityType() {
		return disabilityType;
	}
	public void setDisabilityType(String disabilityType) {
		this.disabilityType = disabilityType;
	}
	public String getIepReading() {
		return iepReading;
	}
	public void setIepReading(String iepReading) {
		this.iepReading = iepReading;
	}
	public String getIepMath() {
		return iepMath;
	}
	public void setIepMath(String iepMath) {
		this.iepMath = iepMath;
	}
	public String getIepBehavior() {
		return iepBehavior;
	}
	public void setIepBehavior(String iepBehavior) {
		this.iepBehavior = iepBehavior;
	}
	public String getGifted() {
		return gifted;
	}
	public void setGifted(String gifted) {
		this.gifted = gifted;
	}
	public String getSection504() {
		return section504;
	}
	public void setSection504(String section504) {
		this.section504 = section504;
	}
	public String getMobility() {
		return mobility;
	}
	public void setMobility(String mobility) {
		this.mobility = mobility;
	}
}
