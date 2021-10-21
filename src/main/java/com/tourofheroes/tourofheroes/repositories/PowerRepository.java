package com.tourofheroes.tourofheroes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.tourofheroes.model.Power;

@Repository
public interface PowerRepository extends JpaRepository<Power, Integer>{

}
