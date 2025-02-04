package com.cts.LibraryManagementSystem.repository;
  
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.LibraryManagementSystem.model.UsersModel;


  
 @Repository 
 public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
	 
	 UsersModel findByUserName(String userName);
	 

	 Optional<UsersModel> findById(int userId);
}
 