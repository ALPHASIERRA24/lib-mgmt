package com.cts.LibraryManagementSystem.repository;
  
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

import com.cts.LibraryManagementSystem.model.UsersModel;
  

  
 @Repository 
 public interface UsersRepository extends JpaRepository<UsersModel,Integer> {
	 
	 List<UsersModel> findByUserName(String userName);
}
 