package com.cts.LibraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.UsersDTO;
import com.cts.LibraryManagementSystem.model.UsersModel;


@Service
public interface UsersService {
	
	public UsersModel addUser(UsersDTO usersDTO);
	
	public List<UsersModel> getAllUser();
	
	public Optional<UsersModel> getUserById(int userId);
	
	public List<UsersModel> getUserByName(String userName);
	
	public UsersModel updateUserById(int userId,UsersDTO usersDTO);
	
	public boolean deleteUserById(int userId);
	
}
