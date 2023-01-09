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
import com.saraya.dto.UserDTO;
import com.saraya.exception.ErrorMessage;
import com.saraya.exception.WeCareException;
import com.saraya.loggin.LoginDTO;
import com.saraya.service.BookingService;
import com.saraya.service.UserService;

@CrossOrigin

@RestController
@RequestMapping("/wecare")
public class UserRestController {
	
@Autowired

UserService userServ;

@Autowired

BookingService bookingServ;
@PostMapping("/users")
public	ResponseEntity createUser( @Valid @RequestBody UserDTO userDTO, Errors error)throws WeCareException{
		
		String response = "";
		
		if(error.hasErrors()) {
			response=error.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			
			ErrorMessage errorMessage = new ErrorMessage();
			errorMessage.setMessage(response);
			return ResponseEntity.ok(errorMessage);
		}
		
		response = userServ.createUser(userDTO);
		return ResponseEntity.ok(response);
	}
@PostMapping("/users/login")
public ResponseEntity<Boolean> loginUser(@Valid @RequestBody LoginDTO loginDTO) throws WeCareException   {
	
//	CoachDTO coachDTO= new CoachDTO();
	
	if(!userServ.loginUser(loginDTO)) {
		
		
		 return ResponseEntity.ok(false);
	     
	  //return ResponseEntity.ok(coachserv.loginCoach(loginDTO));
		 
	}else {
		boolean userFound = userServ.loginUser(loginDTO);
		 return ResponseEntity.ok(userFound);
		
	}


}
@GetMapping ("/users/{userId}")

public ResponseEntity<UserDTO> getUserProfile(@PathVariable String userId){
	
	return ResponseEntity.ok(userServ.getUserProfile(userId));
}

@GetMapping("/users/all")

public List<UserDTO> showAllCoachs(){
	
return userServ.finfAllUsers();
}

@GetMapping("/users/booking/{userId}")
public List<BookingDTO> showMyAppointments(@PathVariable String userId){
	
	return  bookingServ.findBookingByUserId(userId);
}
}
