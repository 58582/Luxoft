package com.luxoft.sb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.sb.dao.TrainerRepoistory;
import com.luxoft.sb.entity.SignUpDTO;
import com.luxoft.sb.entity.Trainer;
import com.luxoft.sb.service.TrainerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/Trainer")
@Api(value = "Trainer Controller for signup and update REST API")
public class TrainerController {
	private static final Log LOGGER = LogFactory.getLog(TrainerController.class);
	@Autowired
	private TrainerRepoistory repo;

	@Autowired
	private TrainerService service;

	@ApiOperation(value = "Signup Trainer REST API")
	@PostMapping("/signup")
	public ResponseEntity<SignUpDTO> signupUser(@RequestBody SignUpDTO signupDto) {

		//username already taken
		try {
			if (repo.existsByUsername(signupDto.getUsername())) {
				return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.BAD_REQUEST);
			}

			Trainer user = new Trainer();

			user.setUsername(signupDto.getUsername());
			user.setPassword(signupDto.getPassword());
			repo.save(user);
			LOGGER.info("Signuping Trainer in TrainerController");
			return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception happened while Signuping Trainer in TrainerController");
			return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.BAD_REQUEST);
		}
	}

	@ApiOperation(value = "Update Trainer Pass REST API")
	@PutMapping("/{id}")
	public ResponseEntity<String> updatePass(@PathVariable int id, @RequestBody Trainer trainer) {
		try {
			service.updatePass(trainer, id);
			LOGGER.info("Updating the trainer Pass in TrainerController");
			return new ResponseEntity<String>("Data Updated Succesfully", HttpStatus.OK);
		} catch (Exception ex) {
			LOGGER.error("Exception happened while Updating the trainer Pass in TrainerController");
			return new ResponseEntity<String>("Search Data Not Found", HttpStatus.NOT_FOUND);
		}
	}
}
