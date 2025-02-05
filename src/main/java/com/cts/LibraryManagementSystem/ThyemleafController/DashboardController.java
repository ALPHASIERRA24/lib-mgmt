package com.cts.LibraryManagementSystem.ThyemleafController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class DashboardController {
	
	@GetMapping("/dashboard")
	public String dashboard() {
			return "dashboard";
		}
	
	@GetMapping("/")
	public String home() {
		return "home";
	}
		
	}
