package com.saraya.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.saraya.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer>{

	Optional<Booking> findByUserId(String userId);
	
	List<Booking> findBookingByUserId(String userId);
	 
	List<Booking> findBookingByCoachId(String coachId); 
	
	//@Query("select from BookingEntity bookingId,appointmentDate,slot from BookingEntity")
	@Query("select u from Booking u where u.userId=?1 and u.appointmentDate=?2 and u.slot=?3")
	 Booking   findAllBookings(String userId, String appointmentDate, String slot);
}
