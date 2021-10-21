package com.tourofheroes.tourofheroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "HEROES")
public class Hero {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IDSEQUENCE")
	@SequenceGenerator(name="IDSEQUENCE", sequenceName="IDSEQUENCE", allocationSize=1)
//	@GeneratedValue
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME", nullable = false, unique = true, length = 30)
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "POWER_ID", referencedColumnName = "POWER_ID")
	private Power power;
	
	
	public Hero() {}
	
	public Hero(String name) {
		this.name = name;
	}
	
	public Hero(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Hero(Integer id, String name, Power power) {
		this.id = id;
		this.name = name;
		this.power = power;
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
