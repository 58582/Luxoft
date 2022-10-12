package com.luxoft.sb.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.luxoft.sb.config.JwtRequest;
import com.luxoft.sb.config.JwtResponse;
import com.luxoft.sb.entity.UserDTO;
import com.luxoft.sb.security.JwtUserDetailsService;
import com.luxoft.sb.util.JwtTokenUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
@Api(value = "Auth controller exposes siginin and signup REST APIs")
public class JwtAuthenticationController {
	private static final Log LOGGER = LogFactory.getLog(JwtAuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@ApiOperation(value = "REST API to Signin or Login user to LearnToday app")
	@PostMapping("/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		LOGGER.info("Token generated in JwtAuthenticationController Successfully");                      
		return ResponseEntity.ok(new JwtResponse(token));
	}

	public void authenticate(String userName, String password) throws Exception {
		try {
			LOGGER.info("authenticated  user in JwtAuthenticationController Success");
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		} catch (DisabledException e) {
			LOGGER.error("User does not exist");
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			LOGGER.error("BadCredentials entered");
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

	@ApiOperation(value = "REST API to signup user to LearnToday app")
	@PostMapping("/signup")
	public ResponseEntity<?> saveUser(@RequestBody UserDTO user) {
		LOGGER.info("User signup in JwtAuthenticationController..");
		return ResponseEntity.ok(jwtUserDetailsService.save(user));
	}

}
