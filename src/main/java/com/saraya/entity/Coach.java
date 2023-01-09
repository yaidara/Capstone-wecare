package com.saraya.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Coach {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) 
	@GenericGenerator(strategy="com.saraya.utitlity.CoachIdGenerator", name = "coachId")
	@GeneratedValue(generator="coachId")
	private String coachId;
	
	private String coachName;
	private String gender;
	private LocalDate  dateOfBirth;
	private String password;
	private Long mobileNumber;
	private String speciality;
	
	public Coach() {
		super();
	}

	public Coach(String coachName, String gender, LocalDate dateOfBirth, String password, Long mobileNumber,
			String speciality) {
		super();
		this.coachName = coachName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.speciality = speciality;
	}
	
	

	public String getCoachId() {
		return coachId;
	}

	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}

	public String getCoachName() {
		return coachName;
	}

	public void setCoachName(String coachName) {
		this.coachName = coachName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	@Override
	public String toString() {
		return "coach [coachid=" + coachId + ", coachName=" + coachName + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", password=" + password + ", mobileNumber=" + mobileNumber + ", speciality="
				+ speciality + "]";
	}
	
	
	
	
}
