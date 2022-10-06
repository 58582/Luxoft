package com.luxoft.sb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.sb.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
