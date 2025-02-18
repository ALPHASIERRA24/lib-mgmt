package com.cts.LibraryManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.cts.LibraryManagementSystem.model.BorrowRecordModel;
import com.cts.LibraryManagementSystem.model.CatalogModel;

public interface BorrowRecordRepository extends JpaRepository<BorrowRecordModel, Integer> {
	
	List<BorrowRecordModel> findByUser_UserId(int userId);
	
	List<BorrowRecordModel> findByBook_BookId(int bookId);
	
	List<BorrowRecordModel> findByDueDateBeforeAndReturnStatus(java.sql.Date currentDate,boolean returnStatus);
	
	@Modifying
	@Query("DELETE FROM BorrowRecordModel b WHERE b.book.bookId = :bookId")
	void deleteByBook_BookId(@Param("bookId") Integer bookId);


}
