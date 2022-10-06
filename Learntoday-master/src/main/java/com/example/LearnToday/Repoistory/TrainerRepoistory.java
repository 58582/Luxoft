package com.example.LearnToday.Repoistory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.LearnToday.Entity.Trainer;

public interface TrainerRepoistory extends JpaRepository<Trainer, Integer> {
	
	Optional<Trainer> findByUsername(String username);

	
	Boolean existsByUsername(String username);



}
