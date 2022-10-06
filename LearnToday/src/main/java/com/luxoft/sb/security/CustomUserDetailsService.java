package com.luxoft.sb.security;
/*
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luxoft.sb.dao.UserRepository;
import com.luxoft.sb.entity.Role;
import com.luxoft.sb.entity.User;

public class CustomUserDetailsService implements UserDetailsService{
     @Autowired
	private UserRepository userRepository;
     
     @Override
 	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	 User  user = userRepository.findByUsername(username)
 				.orElseThrow(() -> new UsernameNotFoundException("Username  is not found" + username));
 		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
 				mapToSimpleGrantedAuthority(user.getRoles())

 		);
 	}
     private Collection<? extends GrantedAuthority> mapToSimpleGrantedAuthority(Set<Role> roles) {
    	 return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

	}

}*/
