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
	
	// FUTURE IMPROVEMENT: MAKE THIS COLUMN A JOIN COLUMN OF THIS TABLE
	@Column(name = "ALTER_EGO", length = 30, nullable = false)
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
