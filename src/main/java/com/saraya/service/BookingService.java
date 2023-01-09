package com.saraya.service;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraya.dto.BookingDTO;
import com.saraya.dto.CoachDTO;
import com.saraya.dto.UserDTO;
import com.saraya.entity.Booking;
import com.saraya.entity.Coach;
import com.saraya.entity.User;
import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WeCareException;
import com.saraya.repository.BookingRepository;
import com.saraya.repository.CoachRepository;
import com.saraya.repository.UserRepository;
import com.saraya.utitlity.MailUtility;


@Service
public class BookingService {
//This method is used to schedule appointment for a given booking id. 
//Invoke findAllBookings() method of book repository.
//If user is not having any specified booking at given slot and date then save booking details in book repository 
//	and invoke sendSchedulingEmail() method of mail utility class and return true otherwise 
	
	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	CoachRepository coachRepoMail;
	@Autowired
	UserRepository userRepoMail;
	
	@Autowired
    MailUtility mail;
    
    
	
	public boolean bookAppointment(String userId, String coachId, String appointmentDate, String slot) throws WeCareException{
		
		
		Booking opti1 = bookingRepo.findAllBookings(userId,appointmentDate,slot);
		
		Booking opti2 = new Booking(userId, coachId, appointmentDate, slot);
		
		Optional<User> userEntity = userRepoMail.findUserByUserId(userId);
//		Coach coach = opti.get();
		User user  = null;
		if(userEntity.isPresent()) {
			user = userEntity.get();
		}
		
	
		Optional<Coach> opti3 = coachRepoMail.findByCoachId(coachId);
//		Coach coach = opti.get();
		Coach coach = null;
		if(opti3.isPresent()) {
			coach = opti3.get();
		}
		
		
		
		if(opti1==null) {
			bookingRepo.save(opti2);
			mail.sendSchedulingEmail(user.getUserName(),coach.getCoachName(),user.getEmail()
					,opti2.getBookingId(), opti2.getSlot(), opti2.getAppointmentDate());
			return true;
				
		}
		throw new WeCareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());
			
   } 
	
	
	public Boolean rescheduleAppointment(Integer bookingId, String appointmentDate, String slot) throws WeCareException{
		
		Booking booking= bookingRepo.getReferenceById(bookingId);
		
		Optional<User> userEntity = userRepoMail.findUserByUserId(booking.getUserId());
//		Coach coach = opti.get();
		User user  = null;
		if(userEntity.isPresent()) {
			user = userEntity.get();
		}
		
	
		Optional<Coach> opti3 = coachRepoMail.findByCoachId(booking.getCoachId());
//		Coach coach = opti.get();
		Coach coach = null;
		if(opti3.isPresent()) {
			coach = opti3.get();
		}
		
	//	Booking opti1 = bookingRepo.findAllBookings(booking.getUserId(),appointmentDate,slot);
		
	
		
		booking.setAppointmentDate(appointmentDate);
		booking.setSlot(slot);
		
	
		
		if(bookingRepo.findAllBookings(booking.getUserId(),appointmentDate,slot)!=booking) {
			bookingRepo.save(booking);
			mail.sendReschedulingEmail(user.getUserName(), coach.getCoachName(),user.getEmail(),booking.getBookingId(),booking.getSlot(),booking.getAppointmentDate());
			return true;
		}
			throw new WeCareException(ExceptionConstants.BOOKING_ALREADY_EXISTS.toString());	
}
	
	public void cancelAppointment(Integer bookingId) {
		
		Booking booking= bookingRepo.getReferenceById(bookingId);
		
		Optional<User> userEntity = userRepoMail.findUserByUserId(booking.getUserId());
//		Coach coach = opti.get();
		User user  = null;
		if(userEntity.isPresent()) {
			user = userEntity.get();
		}
		
			
		Optional<Coach> opti3 = coachRepoMail.findByCoachId(booking.getCoachId());
//		Coach coach = opti.get();
		Coach coach = null;
		if(opti3.isPresent()) {
			coach = opti3.get();
		}
		
		bookingRepo.deleteById(bookingId);
		System.out.println("Your Booking is deleted ");
			mail.sendCancellingEmail(user.getUserName(),coach.getCoachName(),user.getEmail()
					,booking.getBookingId(), booking.getSlot(), booking.getAppointmentDate());
			
				
		}
		
	
	public BookingDTO findByBookingId(Integer bookingId) {
		
		Booking booking= bookingRepo.getReferenceById(bookingId);
		
		if(booking != null) {
			
			BookingDTO bookingDTO = new BookingDTO();
			
			bookingDTO.setCoachId(booking.getCoachId());
			bookingDTO.setUserId(booking.getUserId());
			bookingDTO.setAppointmentDate(booking.getAppointmentDate());
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setSlot(booking.getSlot());
			
			
			return bookingDTO;
			
			}
			return null;
	}
	
	public List<BookingDTO> findBookingByUserId(String userId){
		
		//Booking booking = bookingRepo.findBookingByUserId(userId);
		
		List<Booking> listEntity = bookingRepo.findBookingByUserId(userId);
		List <BookingDTO> listDTO= new ArrayList<>();
		for(Booking bookingEntity: listEntity) {
			
          BookingDTO bookingDTO = new BookingDTO();
			
			bookingDTO.setCoachId(bookingEntity.getCoachId());
			bookingDTO.setUserId(bookingEntity.getUserId());
			bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
			bookingDTO.setBookingId(bookingEntity.getBookingId());
			bookingDTO.setSlot(bookingEntity.getSlot());
		
			listDTO.add(bookingDTO);
		
		}
		return listDTO;
	}
	
public List<BookingDTO> findBookingByCoachId(String coachId){
		
		//Booking booking = bookingRepo.findBookingByUserId(userId);
		
		List<Booking> listEntity = bookingRepo.findBookingByCoachId(coachId);
		List <BookingDTO> listDTO= new ArrayList<>();
		for(Booking bookingEntity: listEntity) {
			
          BookingDTO bookingDTO = new BookingDTO();
			
			bookingDTO.setCoachId(bookingEntity.getCoachId());
			bookingDTO.setUserId(bookingEntity.getUserId());
			bookingDTO.setAppointmentDate(bookingEntity.getAppointmentDate());
			bookingDTO.setBookingId(bookingEntity.getBookingId());
			bookingDTO.setSlot(bookingEntity.getSlot());
		
			listDTO.add(bookingDTO);
		
		}
		return listDTO;
	}
	}

			

		
		
		
	

