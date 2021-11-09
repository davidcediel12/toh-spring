package com.tourofheroes.tourofheroes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
@Builder
public class MyUser {
	@Id
	private String username;
	
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	
	private String password;
	
	@OneToMany(mappedBy = "user")
	private List<UserRole> roles;
	
	
	public MyUser() {}
}
