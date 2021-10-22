package com.tourofheroes.tourofheroes.DTOs;



public class HeroDTO {
	private Integer id;
	private String name;
	private String alterEgo;
	private String powerName;
	private Integer powerId;
	
	public HeroDTO() {}
	
	
	public HeroDTO(String name) {
		super();
		this.name = name;
	}
	
	public HeroDTO(String name, String alterEgo, String powerName, Integer powerId) {
		super();
		this.powerId = powerId;
		this.powerName = powerName;
		this.name = name;
		this.alterEgo = alterEgo;
	}
	
	
	public HeroDTO(Integer id, String name, String alterEgo) {
		super();
		this.id = id;
		this.name = name;
		this.alterEgo = alterEgo;
	}
	
	public HeroDTO(Integer id, String name, String alterEgo, String powerName, Integer powerId) {
		super();
		this.powerName = powerName;
		this.id = id;
		this.name = name;
		this.alterEgo = alterEgo;
		this.powerId = powerId;
	}
	
	
	
	public String getAlterEgo() {
		return alterEgo;
	}


	public void setAlterEgo(String alterEgo) {
		this.alterEgo = alterEgo;
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
