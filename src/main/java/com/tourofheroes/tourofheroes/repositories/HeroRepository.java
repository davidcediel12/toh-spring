package com.tourofheroes.tourofheroes.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.tourofheroes.model.Hero;


@Repository
public interface HeroRepository extends JpaRepository<Hero, Integer>{
	
	public List<Hero> findByOrderByIdAsc();
	public List<Hero> findByNameStartsWithIgnoreCase(String name);
}
