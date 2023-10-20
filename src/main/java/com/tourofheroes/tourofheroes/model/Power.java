package com.tourofheroes.tourofheroes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "POWERS")
@Cacheable
@NoArgsConstructor
@Getter
@Setter
public class Power {

    @Id
    @GeneratedValue
    @Column(name = "POWER_ID")
    private Integer id;


    @Column(name = "NAME", nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "power")
    private Set<Hero> heroes;


    public Power(String name) {
        super();
        this.name = name;
        this.heroes = new HashSet<>();
    }
}
