package com.cts.LibraryManagementSystem.ThyemleafController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.service.UsersService;

@Controller
public class HomeController {
	
	@Autowired
	private UsersService usersService;
	
		@GetMapping("/home")
		public String home() {
			return "home";
		}
		
		@GetMapping("/dashboard")
		public String dashboard(Model model,Principal principal) {		
				String username = principal.getName();		
				UsersModel user = usersService.getUserByName(username);
				model.addAttribute("loggedInUser", user);
				return "dashboard";
			}	
}
