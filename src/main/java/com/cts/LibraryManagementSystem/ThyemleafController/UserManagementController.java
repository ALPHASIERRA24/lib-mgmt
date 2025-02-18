package com.cts.LibraryManagementSystem.ThyemleafController;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.LibraryManagementSystem.dto.UsersDTO;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.service.UsersService;

import lombok.Delegate;

@Controller
@RequestMapping("/admin")
public class UserManagementController {
	@Autowired
	private UsersService usersService;
	
	@GetMapping("/users")
	public String manageUsers(Model model) {
		model.addAttribute("users",usersService.getAllUser());
		return "admin-users";
	}
	
	@PostMapping("/users/add")
	public String addUser(@ModelAttribute UsersDTO userDTO) {
		usersService.addUser(userDTO);
		return "redirect:/admin/users";
	}
	

	@GetMapping("/users/edit/{userId}")

	public String editUserForm(@PathVariable int userId, Model model) {
		Optional<UsersModel> user = usersService.getUserById(userId);
		if (user.isPresent()) {
			model.addAttribute("user", user.get());
			return "edit-user"; 
		} else {
			return "redirect:/admin/users";
		}
	}

	@PostMapping("/users/update/{userId}")
	public String updateUser(@PathVariable int userId, @ModelAttribute UsersDTO usersDTO) {
		usersService.updateUserById(userId, usersDTO);
		return "redirect:/admin/users";
	}

	@GetMapping("/users/delete/{userId}")
	public String deleteUser(@PathVariable int userId) {
		usersService.deleteUserById(userId);
		return "redirect:/admin/users";
	}
}
