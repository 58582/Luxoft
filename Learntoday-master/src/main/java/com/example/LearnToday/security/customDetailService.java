package com.example.LearnToday.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.LearnToday.Entity.Role;
import com.example.LearnToday.Entity.Trainer;
import com.example.LearnToday.Repoistory.TrainerRepoistory;

@Service
public class customDetailService implements UserDetailsService {
	
	@Autowired
	private TrainerRepoistory userRepository;

   

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Trainer user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Username or email is not found" + username));
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				mapToSimpleGrantedAuthority(user.getRoles())

		);
	}

	private Collection<? extends GrantedAuthority> mapToSimpleGrantedAuthority(Set<Role> roles) {

		return roles.stream().map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}

}


// 