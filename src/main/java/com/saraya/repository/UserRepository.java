package com.saraya.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;



import com.saraya.entity.User;

public interface UserRepository extends JpaRepository<User,String>{
	
	
	  Optional <User> findUserByUserId(String userId);

	
}
