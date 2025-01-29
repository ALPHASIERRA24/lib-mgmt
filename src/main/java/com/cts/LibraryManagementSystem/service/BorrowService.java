package com.cts.LibraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.dto.BorrowRecordDTO;
import com.cts.LibraryManagementSystem.model.BorrowRecordModel;

@Service
public interface BorrowService {
	
	List<BorrowRecordModel> getAllBorrowRecord();
	
	BorrowRecordModel addBorrowRecord(BorrowRecordDTO borrowRecordDTO);
	
	BorrowRecordModel updateReturnStatus(int borrowId,BorrowRecordDTO borrowRecordDTO);
	
	Optional<BorrowRecordModel> getBorrowRecordById(int borrowId);
	
	List<BorrowRecordModel> getBorrowRecordByUserId(int userId);
	
	List<BorrowRecordModel> getBorrowRecordByBookd(int bookId);
	
	List<BorrowRecordModel> getOverdueBorrowRecord(java.sql.Date currectDate);
	
	boolean deleteBorrowrecordById(int borrowId);
} 
