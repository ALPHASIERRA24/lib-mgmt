package com.cts.LibraryManagementSystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
	public class HomeController {
	    @GetMapping("/")
	    public String home() {
	        return "redirect:/index.html";  // Redirects "/" to "/index.html"
	    }
	    @GetMapping("/login")
	    public String showLoginPage() {
	        return "redirect:/login.html";  // This will return templates/login.html
	    }
	}

