package com.luxoft.sb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.sb.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(String name);
}
