package com.luxoft.sb.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luxoft.sb.entity.Trainer;

public interface TrainerRepoistory extends JpaRepository<Trainer, Integer>{
	Optional<Trainer> findByUsername(String username);
	Boolean existsByUsername(String username);
}
