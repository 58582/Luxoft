package com.luxoft.sb.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.luxoft.sb.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long>{
	@Query("from UserEntity where username=:username")
	public UserEntity findByUsername(@Param("username") String username);
}
