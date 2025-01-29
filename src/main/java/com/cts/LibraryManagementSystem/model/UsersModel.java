package com.cts.LibraryManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Users_Table")
@Entity
public class UsersModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	private int    userId;
	private String userName;
	private String password;
	private String email;
	private long    phoneNumber;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval = true)
	public List<BorrowRecordModel> borrowRecordModels;
	
	
}
