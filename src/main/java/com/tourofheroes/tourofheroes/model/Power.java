package com.tourofheroes.tourofheroes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "POWERS")
public class Power {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQPOWER")
	@SequenceGenerator(name="SEQPOWER", sequenceName="SEQPOWER", allocationSize=1)
	@Column(name = "POWER_ID")
	private Integer id;
	
	
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	
	@OneToMany(mappedBy = "power")
	private Set<Hero> heroes;
	
	
	public Power() {}
	
	public Power(String name) {
		super();
		this.name = name;
		this.heroes = new HashSet<>();
	}
	
	
	public Power(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
		this.heroes = new HashSet<>();
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
