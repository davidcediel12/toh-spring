package com.tourofheroes.tourofheroes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "HEROES")
@Getter
@Setter
@NoArgsConstructor
public class Hero {
	@Id
	@GeneratedValue
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

	public Hero(String name, String alterEgo) {
		this.name = name;
		this.alterEgo = alterEgo;
	}
}
