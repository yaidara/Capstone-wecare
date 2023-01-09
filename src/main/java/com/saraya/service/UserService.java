package com.saraya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraya.dto.CoachDTO;
import com.saraya.dto.UserDTO;
import com.saraya.entity.Coach;
import com.saraya.entity.User;
import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WeCareException;
import com.saraya.loggin.LoginDTO;
import com.saraya.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	
	UserRepository userRepo;
	
	public String createUser(UserDTO userDTO) {
		User user1 = UserDTO.prepareUserEntity(userDTO);
		userRepo.save(user1);
		
		return "User created successfully,your UserId is " + user1.getUserId();
		
	}
	
public boolean loginUser(LoginDTO loginDTO) throws WeCareException{
		
		Optional<User> opti = userRepo.findUserByUserId(loginDTO.getId());
//		Coach coach = opti.get();
		User user = null;
		if(opti.isPresent()) {
			user = opti.get();
		}
		if(user != null && user.getPassword().equals(loginDTO.getPassword())) {
		
		return true;  
	}
		
	throw new WeCareException(ExceptionConstants.USER_NOT_FOUND.toString());
	
   } 

public UserDTO getUserProfile(String id) {
	
	User user = userRepo.findUserByUserId(id).get();
	if(user != null) {
		
	UserDTO userDTO = new UserDTO();
	
	userDTO.setUserId(user.getUserId());
	userDTO.setCity(user.getCity());
	userDTO.setCountry(user.getCountry());
	userDTO.setEmail(user.getEmail());
	userDTO.setDateofbirth(user.getDateOfBirth());
	userDTO.setMobilenumber(user.getMobileNumber());
	userDTO.setGender(user.getGender());
    userDTO.setPassword(user.getPassword());	
    userDTO.setUserName(user.getUserName());
    userDTO.setPincode(user.getPincode());
    userDTO.setState(user.getState());
	
	return userDTO;
	
	}
	return null;
}

public List <UserDTO> finfAllUsers(){
	
	List<User> listEntity = userRepo.findAll();
	List <UserDTO> listDTO= new ArrayList<>();
	for(User userEntity: listEntity){
		
	
	UserDTO userDTO = new UserDTO();
	
	userDTO.setUserId(userEntity.getUserId());
	userDTO.setCity(userEntity.getCity());
	userDTO.setCountry(userEntity.getCountry());
	userDTO.setEmail(userEntity.getEmail());
	userDTO.setDateofbirth(userEntity.getDateOfBirth());
	userDTO.setMobilenumber(userEntity.getMobileNumber());
	userDTO.setGender(userEntity.getGender());
    userDTO.setPassword(userEntity.getPassword());	
    userDTO.setUserName(userEntity.getUserName());
    userDTO.setPincode(userEntity.getPincode());
    userDTO.setState(userEntity.getState());
    
	listDTO.add(userDTO);
	}
	return listDTO;
	
}
}
