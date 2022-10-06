package com.luxoft.sb.controller;

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

@RestController
@RequestMapping("api/Trainer")
public class TrainerAuthController {
	@Autowired
	private TrainerRepoistory repo;

	@Autowired
	private TrainerService service;
//	
//	@Autowired
//	private JWTTokenGeneratorUtil jwt;
//	
//	@Autowired
//	private AuthenticationManager authenticationManager;

	@PostMapping("/signup")
	public ResponseEntity<SignUpDTO> signupUser(@RequestBody SignUpDTO signupDto) {

		// username already taken
		try {
			if (repo.existsByUsername(signupDto.getUsername())) {
				return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.BAD_REQUEST);
			}

			Trainer user = new Trainer();

			user.setUsername(signupDto.getUsername());
			// user.setPassword(passEncoder.encode(signupDto.getPassword()));
			user.setPassword(signupDto.getPassword());
			// user.setRoles(getRoles(signupDto.getRoles()));
			repo.save(user);

			return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<SignUpDTO>(signupDto, HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updatePass(@PathVariable int id, @RequestBody Trainer trainer) {
		try {
			service.updatePass(trainer, id);
			return new ResponseEntity<String>("Data Updated Succesfully", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<String>("Search Data Not Found", HttpStatus.NOT_FOUND);
		}
	}
	
//	@PostMapping("/signin")
//	public ResponseEntity<JwtAuthResponse> authenticateUser(
//			@RequestBody Trainer trainer) {
//
//		Authentication authentication = authenticationManager
//				.authenticate(new UsernamePasswordAuthenticationToken(trainer.getUsername(), trainer.getPassword()));
//
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String token = jwt.JwtTokenProviderData(authentication);
//
//		return ResponseEntity.ok(new JwtAuthResponse(token, "Bearer"));
//
//	}

}
