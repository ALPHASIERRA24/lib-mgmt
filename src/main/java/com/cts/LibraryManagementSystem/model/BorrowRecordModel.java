package com.cts.LibraryManagementSystem.model;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;       
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "borrow_records")
@Data
@ToString(exclude = "user") 
public class BorrowRecordModel {
   
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BrorowRecord_seq")
	@SequenceGenerator(name = "BrorowRecord_seq", sequenceName = "BrorowRecord_seq", allocationSize = 1)
	private int borrowId;
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable = false)
	@JsonManagedReference
    private UsersModel user;
    
    @ManyToOne
    @JoinColumn(name="book_id",nullable = false)
	@JsonManagedReference
    private CatalogModel book;  
  

    private Date borrowDate;
    private Date dueDate;
    private boolean returnStatus;
 
}