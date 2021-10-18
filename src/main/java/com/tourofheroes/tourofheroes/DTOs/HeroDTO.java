package com.tourofheroes.tourofheroes.DTOs;



public class HeroDTO {
	private Integer id;
	private String name;
	
	
	public HeroDTO() {}
	
	public HeroDTO(String name) {
		super();
		this.name = name;
	}
	
	
	public HeroDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
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
	
	
}
