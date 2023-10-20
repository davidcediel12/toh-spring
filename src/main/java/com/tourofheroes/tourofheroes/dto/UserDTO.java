package com.tourofheroes.tourofheroes.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	private String name;
	private String lastName;
	private String email;
	private String username;
	private String password;
	
	
	public UserDTO(String name, String lastName, String email, String username) {
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
	}
}
