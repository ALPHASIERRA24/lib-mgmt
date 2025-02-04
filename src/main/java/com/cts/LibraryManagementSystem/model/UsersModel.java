package com.cts.LibraryManagementSystem.model;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
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
public class UsersModel implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	private int userId;
	private String userName;
	private String password;
	private String email;
	private long    phoneNumber;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	@JsonBackReference
	@OneToMany(mappedBy = "user", cascade= CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	public List<BorrowRecordModel> borrowRecordModels;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return List.of(new SimpleGrantedAuthority("ROLE_" + role.name()));
	}
	
	@Override
	public String getUsername() {
		return userName;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
	
}
