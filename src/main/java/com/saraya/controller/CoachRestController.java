package com.saraya.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.saraya.dto.BookingDTO;
import com.saraya.dto.CoachDTO;
import com.saraya.entity.Coach;
import com.saraya.exception.ErrorMessage;

import com.saraya.exception.WeCareException;
import com.saraya.loggin.LoginDTO;
import com.saraya.service.BookingService;
import com.saraya.service.CoachService;

@CrossOrigin
@RestController
@RequestMapping("/wecare")
public class CoachRestController {

	@Autowired
	CoachService coachserv;
	
@Autowired
	
	BookingService bookingServ;
	
	@PostMapping("/coachs") 
	public ResponseEntity createCoach(@Valid @RequestBody CoachDTO coachDTO,Errors errors) throws WeCareException{
		
    String response = "";
		
		if(errors.hasErrors()) { 
			response=errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMessage(response);
			return ResponseEntity.ok(errorMessage);
		}
		
		response = coachserv.createCoach(coachDTO);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/coachs/login")
	public ResponseEntity<Boolean> loginCoach(@Valid @RequestBody LoginDTO loginDTO) throws WeCareException   {
		
	//	CoachDTO coachDTO= new CoachDTO();
		
		if(!coachserv.loginCoach(loginDTO)) {
			
			
			 return ResponseEntity.ok(false);
		     
		  //return ResponseEntity.ok(coachserv.loginCoach(loginDTO));
			 
		}else {
			boolean coachFound = coachserv.loginCoach(loginDTO);
			 return ResponseEntity.ok(coachFound);
			
		}

		
		
		
		
//		boolean coachFound = coachserv.loginCoach(loginDTO);
//		 return ResponseEntity.ok(coachFound);
		 
		 
		
//		 if(!coachserv.loginCoach(loginDTO)) {
//			 return ResponseEntity.ok(false);
//		 }
//		 return ResponseEntity.ok(coachserv.loginCoach(loginDTO));
		
	}
	@GetMapping("/coachs/{coachId}")
	
	public ResponseEntity<CoachDTO> getCoachProfile(@PathVariable String coachId){
		
		return ResponseEntity.ok(coachserv.getCoachProfile(coachId));
	}
	
	@GetMapping("/coachs/all")
	
		public List<CoachDTO> showAllCoachs(){
			
		return coachserv.finfAllCoachs();
		}
	@GetMapping("/coachs/booking/{coachId}")
	List<BookingDTO> showMySchedule(@PathVariable String coachId){
		
		return bookingServ.findBookingByCoachId(coachId);
	}
}

