package com.tourofheroes.tourofheroes.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idPowerGenerator")
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


    public Power(String name) {
        super();
        this.name = name;
        this.heroes = new HashSet<>();
    }
}
