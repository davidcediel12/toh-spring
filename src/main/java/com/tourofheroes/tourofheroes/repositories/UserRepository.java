package com.tourofheroes.tourofheroes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.tourofheroes.model.MyUser;


@Repository
public interface UserRepository extends JpaRepository<MyUser, String>{
	
}
