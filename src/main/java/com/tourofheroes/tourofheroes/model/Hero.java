package com.tourofheroes.tourofheroes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

@Entity
@Table(name = "HEROES")
@Getter
@Setter
@NoArgsConstructor
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

	public Hero(String name, String alterEgo) {
		this.name = name;
		this.alterEgo = alterEgo;
	}
}
