package com.cts.LibraryManagementSystem.dto;



import com.cts.LibraryManagementSystem.model.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {

	private String userName;
	private String password;
	private String email;
	private long   phoneNumber;
	private Role role;

}
