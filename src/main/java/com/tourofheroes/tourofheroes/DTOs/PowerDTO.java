package com.tourofheroes.tourofheroes.DTOs;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PowerDTO {

	private Integer id;
	private String name;
	

	public PowerDTO(String name) {
		super();
		this.name = name;

	}
}
