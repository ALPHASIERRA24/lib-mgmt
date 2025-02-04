package com.cts.LibraryManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cts.LibraryManagementSystem.model.UsersModel;
import com.cts.LibraryManagementSystem.repository.UsersRepository;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UsersRepository userRepo;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UsersModel user= userRepo.findByUserName(userName);
		System.out.println(user);
		if(user == null) {
			System.out.println("hello");
			throw new UsernameNotFoundException("User not found" +userName);
		}
		return user;
	}

}
