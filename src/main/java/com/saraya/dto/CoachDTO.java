package com.saraya.dto;

import java.time.LocalDate;

import com.saraya.entity.Coach;

public class CoachDTO {

private String coachId;
	
	private String coachName;
	private String gender;
	private LocalDate  dateOfBirth;
	private String password;
	private Long mobileNumber;
	private String speciality;
	
	public CoachDTO() {
		
	}

	public CoachDTO(String coachId,String coachName, String gender, LocalDate dateOfBirth, String password, Long mobileNumber,
			String speciality) {
		super();
		this.coachId=coachId;
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
		return "coach [coach_id=" + coachId + ", coachName=" + coachName + ", gender=" + gender + ", date_of_birth="
				+ dateOfBirth + ", password=" + password + ", mobile_number=" + mobileNumber + ", speciality="
				+ speciality + "]";
	}
	
	public static Coach prepareCoachEntity(CoachDTO coachDTO) {
		
		Coach coachEntity = new Coach();
		
		coachEntity.setDateOfBirth(coachDTO.getDateOfBirth());
		coachEntity.setGender(coachDTO.getGender());
		coachEntity.setMobileNumber(coachDTO.getMobileNumber());
		coachEntity.setPassword(coachDTO.getPassword());
		coachEntity.setCoachName(coachDTO.getCoachName());
		coachEntity.setSpeciality(coachDTO.getSpeciality());
		coachEntity.setCoachId(coachDTO.getCoachId());
		
		return coachEntity;
	}
	
}
