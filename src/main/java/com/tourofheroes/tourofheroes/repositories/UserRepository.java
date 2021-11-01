package com.tourofheroes.tourofheroes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tourofheroes.tourofheroes.model.MyUser;

public interface UserRepository extends JpaRepository<MyUser, String>{
	
}
