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

import lombok.Data;

@Entity
@Data
@Table(name = "USER_ROLES")
public class UserRole {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="idRolesGenerator")
	@GenericGenerator(
	        name = "idRolesGenerator",
	        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
	        parameters = {
	                @Parameter(name = "sequence_name", value = "ROLES_SEQ"),
	                @Parameter(name = "initial_value", value = "1"),
	                @Parameter(name = "increment_size", value = "1")
	        }
	)
	@Column(name = "ID")
	private Integer id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "user_username", referencedColumnName = "username")
	private MyUser user;
}
