package com.cts.LibraryManagementSystem.service;


import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adi.datajpa.dao.repository.UserRepository;
import com.cts.LibraryManagementSystem.dto.UsersDTO;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired 
	private UsersRepository usersRepository;

	@Override
	public UsersModel addUser(UsersDTO usersDTO) {
		UsersModel user = new UsersModel();
		user.setUserName(usersDTO.getUserName());
		user.setPassword(usersDTO.getPassword());
		user.setEmail(usersDTO.getEmail());
		user.setPhoneNumber(usersDTO.getPhoneNumber());
		return usersRepository.save(user);
	}

	@Override
	public List<UsersModel> getAllUser() {
		return usersRepository.findAll();
	}

	@Override
	public Optional<UsersModel> getUserById(int userId) {
		return usersRepository.findById(userId);
	}

	@Override
	public List<UsersModel> getUserByName(String userName) {
		return usersRepository.findByUserName(userName);
	}

	@Override
	public UsersModel updateUserById(int userId, UsersDTO usersDTO) {
		Optional<UsersModel> userOpt= usersRepository.findById(userId);
		if(userOpt.isPresent()) {
			UsersModel user = userOpt.get();
			user.setUserName(usersDTO.getUserName());
			user.setPassword(usersDTO.getPassword());
			user.setEmail(usersDTO.getEmail());
			user.setPhoneNumber(usersDTO.getPhoneNumber());
			return usersRepository.save(user);	
		}
		throw new RuntimeException("User Not Found with ID: " + userId);		
	}

	@Override
	public boolean deleteUserById(int userId) {
		Optional<UsersModel> user = usersRepository.findById(userId);
		if (user.isPresent()) {
			usersRepository.deleteById(userId);
			return true;
		}
		return false;
	}
	

	
	



}
