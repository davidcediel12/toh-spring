package com.tourofheroes.tourofheroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "HEROES")
public class Hero {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idHeroGenerator")
	@GenericGenerator(
	        name = "idHeroGenerator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "WIKI_SEQUENCE"),
	                @Parameter(name = "initial_value", value = "1000"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME", nullable = false, unique = true, length = 30)
	private String name;
	
	
	@ManyToOne
	@JoinColumn(name = "POWER_ID", referencedColumnName = "POWER_ID")
	private Power power;
	
	// FUTURE IMPROVEMENT: MAKE THIS COLUMN A JOIN COLUMN OF THIS TABLE
	@Column(name = "ALTER_EGO", length = 30, nullable = true)
	private String alterEgo;
	
	public Hero() {}
	
	public Hero(String name, String alterEgo) {
		this.name = name;
		this.alterEgo = alterEgo;
	}
	
	public Hero(Integer id, String name, String alterEgo) {
		this.id = id;
		this.name = name;
		this.alterEgo = alterEgo;
	}
	
	public Hero(Integer id, String name, String alterEgo, Power power) {
		this.id = id;
		this.name = name;
		this.alterEgo = alterEgo;
		this.power = power;
	}
	
	
	
	public String getAlterEgo() {
		return alterEgo;
	}

	public void setAlterEgo(String alterEgo) {
		this.alterEgo = alterEgo;
	}

	public Power getPower() {
		return power;
	}

	public void setPower(Power power) {
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
