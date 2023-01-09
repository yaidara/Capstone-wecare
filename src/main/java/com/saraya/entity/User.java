package com.saraya.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {

	@Id
	//@GeneratedValue(strategy=GenerationType.) 
	@GenericGenerator(strategy="com.saraya.utitlity.UserIdGenerator", name = "userId")
	@GeneratedValue(generator="userId")
	private String userId;
	
	private String userName;
	private String gender;
	private LocalDate dateOfBirth;
	private String password;
	private Long mobileNumber;
	private String email;
	private Integer pincode;
	private String city;
	private String state;
	private String country;
	
	
	public User() {
		super();
}


	public User(String userName, String gender, LocalDate dateOfBirth, String password, Long mobileNumber, String email,
			Integer pincode, String city, String state, String country) {
		super();
		this.userName = userName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.password = password;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.pincode = pincode;
		this.city = city;
		this.state = state;
		this.country = country;
	}
	


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
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


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getPincode() {
		return pincode;
	}


	public void setPincode(Integer pincode) {
		this.pincode = pincode;
	}


	public String getCity() {
		return city;
	}


	public void setCity(String city) {
		this.city = city;
	}


	public String getState() {
		return state;
	}


	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}


	public void setCountry(String country) {
		this.country = country;
	}


	@Override
	public String toString() {
		return "User [userid=" + userId + ", userName=" + userName + ", gender=" + gender + ", dateofbirth=" + dateOfBirth
				+ ", password=" + password + ", mobilenumber=" + mobileNumber + ", email=" + email + ", pincode="
				+ pincode + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}
	
	
}