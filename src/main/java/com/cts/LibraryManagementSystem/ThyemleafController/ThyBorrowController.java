package com.cts.LibraryManagementSystem.ThyemleafController;

import java.security.Principal;
import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping
	public String viewAllBorrowRecords(Model model) {
		List<BorrowRecordModel> borrowRecordModels = borrowService.getAllBorrowRecord();
		model.addAttribute("borrowRecords",borrowRecordModels);
		return "borrow-records";
	}
	
	@GetMapping("/borrow-history")
	public String getUserBorrowHistory(Model model,Principal principal) {
		UsersModel usersModel=usersService.getUserByName(principal.getName());
		if (usersModel == null) {
			throw new RuntimeException("User not found");
		}
		
		List<BorrowRecordModel> borrowRecordModels=borrowService.getBorrowRecordByUserId(usersModel.getUserId());
		model.addAttribute("borrowRecords",borrowRecordModels);

		return "borrow-records";
	}
	
	@GetMapping("/request/{bookId}")
	public String showBorrowRequestForm(@PathVariable int bookId,Model model) {
	Optional<CatalogModel> book = catalogService.getBookById(bookId);
    if (book.isEmpty()) {
        throw new RuntimeException("Book not found");
    }
		model.addAttribute("book",book.get());
		model.addAttribute("borrowRecord",new BorrowRecordDTO());
		return "borrow-request";
	}
	

	@PostMapping("/request/{bookId}")
	public String requestBook(@PathVariable int bookId, Principal principal) {
	    UsersModel usersModel = usersService.getUserByName(principal.getName());
	    if (usersModel == null) {
	        throw new RuntimeException("User not found");
	    }
	    
	    BorrowRecordDTO borrowRecordDTO= new BorrowRecordDTO();
	    borrowRecordDTO.setBookId(bookId);
	    borrowRecordDTO.setUserId(usersModel.getUserId());
	    borrowRecordDTO.setReturnStatus(false);

	    borrowService.addBorrowRecord(borrowRecordDTO);

	    return "redirect:/borrow-records";
	}

}
