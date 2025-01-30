package com.cts.LibraryManagementSystem.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class BorrowRecordDTO {
	
	private int userId;
	private int bookId;
    private Date dueDate;
    private boolean returnStatus;
	
}
