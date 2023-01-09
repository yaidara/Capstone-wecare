package com.saraya.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saraya.dto.CoachDTO;

import com.saraya.entity.Coach;
import com.saraya.exception.ExceptionConstants;
import com.saraya.exception.WeCareException;
import com.saraya.loggin.LoginDTO;
import com.saraya.repository.CoachRepository;


@Service
public class CoachService {

	@Autowired
	CoachRepository coachRepo;
	
	public String createCoach(CoachDTO coachDTO) {
		Coach coach1 = CoachDTO.prepareCoachEntity(coachDTO);
		
		 coachRepo.save(coach1);
		 
		 return "Coach created successfully,your CoachId is " + coach1.getCoachId();
	}
	
	public boolean loginCoach(LoginDTO loginDTO) throws WeCareException{
		
		Optional<Coach> opti = coachRepo.findByCoachId(loginDTO.getId());
//		Coach coach = opti.get();
		Coach coach = null;
		if(opti.isPresent()) {
			coach = opti.get();
		}
		if(coach != null && coach.getPassword().equals(loginDTO.getPassword())) {
		
		return true;  
	}
		
	throw new WeCareException(ExceptionConstants.COACH_NOT_FOUND.toString());
	
   } 
	
	public CoachDTO getCoachProfile(String id) {
		
		Coach coach = coachRepo.findByCoachId(id).get();
		if(coach != null) {
			
		CoachDTO coachDTO = new CoachDTO();
		
		coachDTO.setCoachId(coach.getCoachId());
		coachDTO.setCoachName(coach.getCoachName());
		coachDTO.setDateOfBirth(coach.getDateOfBirth());
		coachDTO.setGender(coach.getGender());
		coachDTO.setMobileNumber(coach.getMobileNumber());
		coachDTO.setPassword(coach.getPassword());
		coachDTO.setSpeciality(coach.getSpeciality());
		
		return coachDTO;
		
		}
		return null;
	}
	
	public List <CoachDTO> finfAllCoachs(){
		
		List<Coach> listEntity = coachRepo.findAll();
		List <CoachDTO> listDTO= new ArrayList<>();
		for(Coach coachEntity: listEntity){
			
		
		CoachDTO coachDTO = new CoachDTO();
		
		coachDTO.setCoachId(coachEntity.getCoachId());
		coachDTO.setCoachName(coachEntity.getCoachName());
		coachDTO.setDateOfBirth(coachEntity.getDateOfBirth());
		coachDTO.setGender(coachEntity.getGender());
		coachDTO.setMobileNumber(coachEntity.getMobileNumber());
		coachDTO.setPassword(coachEntity.getPassword());
		coachDTO.setSpeciality(coachEntity.getSpeciality());
		
		listDTO.add(coachDTO);
		}
		return listDTO;
	}
}