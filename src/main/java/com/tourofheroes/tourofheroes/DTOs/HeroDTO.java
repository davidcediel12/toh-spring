package com.tourofheroes.tourofheroes.DTOs;



public class HeroDTO {
	private Integer id;
	private String name;
	private String powerName;
	private Integer powerId;
	
	public HeroDTO() {}
	
	
	public HeroDTO(String name) {
		super();
		this.name = name;
	}
	
	public HeroDTO(String name, String powerName, Integer powerId) {
		super();
		this.powerId = powerId;
		this.powerName = powerName;
		this.name = name;
	}
	
	
	public HeroDTO(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public HeroDTO(Integer id, String name, String powerName, Integer powerId) {
		super();
		this.powerName = powerName;
		this.id = id;
		this.name = name;
		this.powerId = powerId;
	}
	
	
	
	public Integer getPowerId() {
		return powerId;
	}


	public void setPowerId(Integer powerId) {
		this.powerId = powerId;
	}


	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}


	public String getPowerName() {
		return powerName;
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
