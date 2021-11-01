package com.tourofheroes.tourofheroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
@AllArgsConstructor
public class MyUser {
	@Id
	private String username;
	
	@Column(unique = true)
	private String email;
	private String name;
	private String lastName;
	
	private String password;
	
	public MyUser() {}
}
