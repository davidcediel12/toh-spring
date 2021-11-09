package com.tourofheroes.tourofheroes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tourofheroes.tourofheroes.model.UserRole;

@Repository
public interface UserRolesRepository extends JpaRepository<UserRole, Integer>{

}
