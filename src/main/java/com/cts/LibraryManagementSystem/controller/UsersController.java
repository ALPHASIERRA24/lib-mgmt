package com.cts.LibraryManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.LibraryManagementSystem.dto.UsersDTO;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.service.UsersService;

@RestController
@RequestMapping("/users")
public class UsersController {
	
	@Autowired
	private UsersService usersService;
	
	@PostMapping
	public UsersModel addUser(@RequestBody UsersDTO userDTO) {
		return usersService.addUser(userDTO);
	}
	
	@GetMapping("/allUser")
	public List<UsersModel> getAllUser(UsersModel userModel){
		return usersService.getAllUser();
	}
	
	@GetMapping("/{userId}")
	public Optional<UsersModel> getUserById(@PathVariable int userId){
		return usersService.getUserById(userId);
	}
	
	@GetMapping("/name/{userName}")
	public List<UsersModel> getUserByName(@PathVariable String userName){
		return usersService.getUserByName(userName);
	}
	
	@PutMapping("/{userId}")
	public UsersModel updateByID(@PathVariable int userId,@RequestBody UsersDTO userDTO){
		return usersService.updateUserById(userId,userDTO);
	}
	
	@DeleteMapping("/{userId}")
	public boolean deleteById(@PathVariable int userId) {
		return usersService.deleteUserById(userId);
	}

}
