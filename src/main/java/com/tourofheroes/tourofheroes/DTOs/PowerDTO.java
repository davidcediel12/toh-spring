package com.tourofheroes.tourofheroes.DTOs;



public class PowerDTO {
	private Integer id;
	private String name;
	
	
	public PowerDTO() {} 
	
	
	public PowerDTO(String name) {
		super();
		this.name = name;

	}
	
	public PowerDTO(Integer id, String name) {
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
