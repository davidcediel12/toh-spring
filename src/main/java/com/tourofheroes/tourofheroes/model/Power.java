package com.tourofheroes.tourofheroes.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "POWERS")
@Cacheable
public class Power {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idPowerGenerator")
	@GenericGenerator(
	        name = "idPowerGenerator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "WIKI_SEQUENCE"),
	                @Parameter(name = "initial_value", value = "1000"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
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
