package com.saraya.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.saraya.entity.Booking;

public class BookingDTO {

	private Integer bookingId;
	private String userId;
	private String coachId;

	private String appointmentDate;
	private String slot;
	
	public BookingDTO() {
		super();
	}

	
	public BookingDTO(Integer bookingId, String userId, String coachId, String appointmentDate, String slot) {
		super();
		this.bookingId = bookingId;
		this.userId = userId;
		this.coachId = coachId;
		this.appointmentDate = appointmentDate;
		this.slot = slot;
	}


	public Integer getBookingId() {
		return bookingId;
	}


	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}


	public String getUserId() {
		return userId;
	}


	public void setUserId(String userId) {
		this.userId = userId;
	}


	public String getCoachId() {
		return coachId;
	}


	public void setCoachId(String coachId) {
		this.coachId = coachId;
	}


	public String getAppointmentDate() {
		return appointmentDate;
	}


	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}


	public String getSlot() {
		return slot;
	}


	public void setSlot(String slot) {
		this.slot = slot;
	}


	@Override
	public String toString() {
		return "BookingDTO [bookingId=" + bookingId + ", userId=" + userId + ", coachId=" + coachId
				+ ", appointmentDate=" + appointmentDate + ", slot=" + slot + "]";
	}


	public static Booking prepareBookingEntity(BookingDTO bookingDTO) {
		
		Booking bookingEntity = new Booking();
		
		bookingEntity.setCoachId(bookingDTO.getCoachId());
		bookingEntity.setUserId(bookingDTO.getUserId());
		bookingEntity.setAppointmentDate(bookingDTO.getAppointmentDate());
		bookingEntity.setSlot(bookingDTO.getSlot());
		
		return bookingEntity;
	}

}
