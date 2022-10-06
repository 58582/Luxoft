package com.luxoft.sb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luxoft.sb.dao.TrainerRepoistory;
import com.luxoft.sb.entity.Trainer;

@Service
public class TrainerServiceImpl implements TrainerService{
	@Autowired
	private TrainerRepoistory repo;
	@Override
	public Trainer updatePass(Trainer trainer, int id) {
		Trainer trainerData = repo.findById(id).get();
		trainerData.setUsername(trainer.getUsername());
		trainerData.setPassword(trainer.getPassword());
		return repo.save(trainerData);
	}

}
