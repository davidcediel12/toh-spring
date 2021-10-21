package com.tourofheroes.tourofheroes.DTOs;

import java.util.ArrayList;
import java.util.List;

public class PowerDTO {
	private Integer id;
	private String name;
	private List<HeroDTO> heroes;
	
	
	
	public PowerDTO() {} 
	
	
	public PowerDTO(String name) {
		super();
		this.name = name;
		this.heroes = new ArrayList<>();
	}
	
	public PowerDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.heroes = new ArrayList<>();
	}
	
	public PowerDTO(Integer id, String name, List<HeroDTO> heroes) {
		super();
		this.id = id;
		this.name = name;
		this.heroes = heroes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<HeroDTO> getHeroes() {
		return heroes;
	}
	public void setHeroes(List<HeroDTO> heroes) {
		this.heroes = heroes;
	}
	
}
