package com.tourofheroes.tourofheroes.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HeroDTO {

	private Integer id;
	private String name;
	private String alterEgo;
	private String powerName;
	private Integer powerId;
}
