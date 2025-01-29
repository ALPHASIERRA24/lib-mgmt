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

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;
import com.cts.LibraryManagementSystem.model.BorrowRecordModel;
import com.cts.LibraryManagementSystem.service.BorrowService;



@RestController
@RequestMapping("/borrow-records")
public class BorrowController {
	
	@Autowired
	private BorrowService borrowService;
	
	@GetMapping
	public List<BorrowRecordModel> getAllBorrowRecord(){
		return borrowService.getAllBorrowRecord();
	}
	
	@PostMapping
	public BorrowRecordModel addBorrowRecordModel(@RequestBody BorrowRecordDTO borrowRecordDTO) {
		return borrowService.addBorrowRecord(borrowRecordDTO);
	}
	
	@PutMapping("/{borrowId}")
	public BorrowRecordModel updateReturnStatus(@PathVariable int borrowId,@RequestBody BorrowRecordDTO borrowRecordDTO ) {
		return borrowService.updateReturnStatus(borrowId,borrowRecordDTO );
	}
	
	@GetMapping("/user/{userId}")
	public List<BorrowRecordModel> getBorrowrecordsByUserId(@PathVariable int userId){
		return borrowService.getBorrowRecordByUserId(userId) ;
	}
	
	@GetMapping("{borrowId}")
	public Optional<BorrowRecordModel> getBorrowrecordById(@PathVariable int borrowId){
		return borrowService.getBorrowRecordById(borrowId);
	}
	
	@GetMapping("/book/{bookId}")
	public List<BorrowRecordModel> getBorrowRecordsByBookId(@PathVariable int bookId){
		return borrowService.getBorrowRecordByBookd(bookId);
	}
	
	@GetMapping("/overdue")
	public List<BorrowRecordModel> getOverdueBorrowRecords(){
		java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
		return borrowService.getOverdueBorrowRecord(currentDate);
	}
	
	@DeleteMapping("/{borrowId}")
	public String deleteBorrowrecordsById(@PathVariable int borrowId) {
		boolean isDeleted = borrowService.deleteBorrowrecordById(borrowId);
		return isDeleted ? "Borrow record deleted successfully." : "Borrow record not found.";
	}

	

}
