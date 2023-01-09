package com.saraya.controller;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.saraya.exception.WeCareException;
import com.saraya.service.BookingService;

@CrossOrigin
@RestController
@RequestMapping("/wecare")
public class BookingRestController {

	
	
	@Autowired
	
	BookingService bookingServ;
	
	@PostMapping("/users/{userId}/booking/{coachId}")
	
	ResponseEntity<Boolean> bookAppointment(@PathVariable String userId, @PathVariable String coachId, @RequestParam  String appointmentDate, @RequestParam String slot) throws WeCareException{
		
		
//		if(!bookingServ.bookAppointment(userId, coachId, appointmentDate, slot)) {
//			
//			
			boolean myBooking = bookingServ.bookAppointment(userId, coachId, appointmentDate, slot);

			return ResponseEntity.ok(myBooking);
	}
	
	@PutMapping("/booking/{bookingId}")
	
	ResponseEntity<Boolean> rescheduleAppointment(@PathVariable Integer bookingId,@RequestParam String slot,@RequestParam  String appointmentDate) throws WeCareException{
		
		boolean res = bookingServ.rescheduleAppointment(bookingId, appointmentDate, slot);

		return ResponseEntity.ok(res);
	}
	
   @DeleteMapping("/booking/{bookingId}")
	
	public ResponseEntity<Void> cancelAppointment(@PathVariable Integer bookingId){
		
    bookingServ.cancelAppointment(bookingId);
//	   
        return  ResponseEntity.ok().build();
//	   
     
	}
   
}
