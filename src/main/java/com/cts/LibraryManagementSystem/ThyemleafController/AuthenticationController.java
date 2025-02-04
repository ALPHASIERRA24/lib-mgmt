package com.cts.LibraryManagementSystem.ThyemleafController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.cts.LibraryManagementSystem.dto.UsersDTO;
import com.cts.LibraryManagementSystem.service.UsersService;

@Controller
@RequestMapping("/users")
public class AuthenticationController {
	
	@Autowired
	UsersService userService;
	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/register")
	public String showRegistration(Model model) {
		model.addAttribute("user", new UsersDTO());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@ModelAttribute("user") UsersDTO userDTO, RedirectAttributes redirectAttributes) {
		try {
			userService.addUser(userDTO);
			redirectAttributes.addAttribute("success", "User registered successfully!");
			return "redirect:/users/login";
		}catch(Exception e) {
			System.out.println(e);
			redirectAttributes.addAttribute("error", "User not registered!");
			return "register";
		}
	}
	
}
