package com.cts.LibraryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.LibraryManagementSystem.model.BorrowRecordModel;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecordModel, Integer> {
	
	List<BorrowRecordModel> findByUser_UserId(int userId);
	
	List<BorrowRecordModel> findByBook_BookId(int bookId);
	
	List<BorrowRecordModel> findByDueDateBeforeAndReturnStatus(java.sql.Date currentDate,boolean returnStatus);

}
