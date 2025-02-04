package com.cts.LibraryManagementSystem.ThyemleafController;

import java.security.Principal;
import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestMapping;

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;

import com.cts.LibraryManagementSystem.model.BorrowRecordModel;
import com.cts.LibraryManagementSystem.model.CatalogModel;
import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.service.BorrowService;
import com.cts.LibraryManagementSystem.service.CatalogService;
import com.cts.LibraryManagementSystem.service.UsersService;

@Controller
@RequestMapping("/borrow-records")
public class ThyBorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@Autowired
	private UsersService usersService;
	
	@Autowired
	private CatalogService catalogService;
	
	@GetMapping("/borrow-history")
	public String getUserBorrowHistory(Model model,Principal principal) {
		UsersModel usersModel=usersService.getUserByName(principal.getName());
		if (usersModel == null) {
			throw new RuntimeException("User not found");
		}

		List<BorrowRecordModel> borrowRecordModels=borrowService.getBorrowRecordByUserId(usersModel.getUserId());
		model.addAttribute("borrowRecords",borrowRecordModels);

		return "borrow-history";
	}
	
	@GetMapping("/request")
	public String showBorrowRequestForm(Model model) {
		List<CatalogModel> books=catalogService.getAllBooks();
		model.addAttribute("books",books);
		return "borrow-request";
	}
	

	@PostMapping("/request")
	public String requestBook(@ModelAttribute BorrowRecordDTO borrowRecordDTO, Principal principal) {
	    UsersModel usersModel = usersService.getUserByName(principal.getName());
	    if (usersModel == null) {
	        throw new RuntimeException("User not found");
	    }

	    borrowRecordDTO.setUserId(usersModel.getUserId());
	    borrowRecordDTO.setReturnStatus(false);

	    borrowService.addBorrowRecord(borrowRecordDTO);

	    return "redirect:/borrow-records/user/borrow-history";
	}

}
