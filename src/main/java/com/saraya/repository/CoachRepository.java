package com.saraya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.saraya.entity.Coach;

public interface CoachRepository extends JpaRepository<Coach,String>{
	
	
	 Optional<Coach> findByCoachId(String coachId);
	
	//Optional <Coach> findByCoachId(String coach_id);

}
