package com.example.LearnToday.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.LearnToday.Entity.Trainer;
import com.example.LearnToday.Repoistory.TrainerRepoistory;
import com.example.LearnToday.service.TrainerService;

@Service
public class TrainerServiceImpl implements TrainerService {

	@Autowired
	private TrainerRepoistory repo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	public Trainer updatePass(Trainer trainer, int id) {

		Trainer trainerData = repo.findById(id).get();
		trainerData.setUsername(trainer.getUsername());
		trainerData.setPassword(passwordEncoder.encode(trainer.getPassword()));
		return repo.save(trainerData);
	}

}
