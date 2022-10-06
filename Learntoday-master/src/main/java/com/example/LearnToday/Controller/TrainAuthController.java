package com.example.LearnToday.Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.LearnToday.Entity.Role;
import com.example.LearnToday.Entity.Trainer;
import com.example.LearnToday.Repoistory.TrainerRepoistory;
import com.example.LearnToday.payload.SignUpDTO;
import com.example.LearnToday.service.TrainerService;
import com.example.LearnToday.utils.JWTTokenGeneratorUtil;

@RestController
@RequestMapping("/login")
public class TrainAuthController {

	@Autowired
	private JWTTokenGeneratorUtil jwt;

	@Autowired
	private TrainerRepoistory repo;

	@Autowired
	private TrainerService service;

	@Autowired
	private BCryptPasswordEncoder passEncoder;

	@Autowired
	private com.example.LearnToday.Repoistory.RoleRepoistory roleRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	// @PreAuthorize("hasRole('ROLE_TRAINER')")
	@PostMapping("/signin")
	public ResponseEntity<com.example.LearnToday.payload.JwtAuthResponse> authenticateUser(
			@RequestBody Trainer trainer) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(trainer.getUsername(), trainer.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token = jwt.JwtTokenProviderData(authentication);

		return ResponseEntity.ok(new com.example.LearnToday.payload.JwtAuthResponse(token, "Bearer"));

	}

	 //@PreAuthorize("hasRole('ROLE_USER')")
	@PostMapping("/signup")
	public ResponseEntity<String> signupUser(@RequestBody SignUpDTO signupDto) {

		// username already taken
		if (repo.existsByUsername(signupDto.getUsername())) {
			return new ResponseEntity<String>("Username already taken", HttpStatus.BAD_REQUEST);
		}

		Trainer user = new Trainer();

		user.setUsername(signupDto.getUsername());
		user.setPassword(passEncoder.encode(signupDto.getPassword()));

		user.setRoles(getRoles(signupDto.getRoles()));
		repo.save(user);

		return new ResponseEntity<String>("User signedup successfully", HttpStatus.OK);

	}

	@PreAuthorize("hasRole('ROLE_TRAINER')")
	@PutMapping("/api/{id}/updatePass")
	public ResponseEntity<Trainer> updatePass(@PathVariable int id, @RequestBody Trainer trainer) {

		System.out.println("service.updatePass(trainer, id)" + service.updatePass(trainer, id));

		return new ResponseEntity<Trainer>(service.updatePass(trainer, id), HttpStatus.OK);

		// Trainer tainerData = repo.findBy(id);

	}

	private Set<Role> getRoles(String[] roles) {
		Set<Role> userRoles = new HashSet<>();
		for (String role : roles) {
			userRoles.add(roleRepo.findByName(role));
		}
		return userRoles;
	}

}
