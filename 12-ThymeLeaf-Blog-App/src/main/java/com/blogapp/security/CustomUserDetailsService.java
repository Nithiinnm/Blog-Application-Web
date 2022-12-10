package com.blogapp.security;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogapp.entity.User;
import com.blogapp.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepo;
	
	public CustomUserDetailsService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		User userByEmail = userRepo.findByEmail(email);
		
		List<SimpleGrantedAuthority> roles = userByEmail.getRoles().stream()
		.map((role) -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
		
		if(Objects.nonNull(userByEmail)) {
			
			org.springframework.security.core.userdetails.User authenticatedUser = new org.springframework.security.core.userdetails
																				   .User(userByEmail.getEmail(), 
																					     userByEmail.getPassword(), 
																					     roles);
			return authenticatedUser;
		}
		else {
			throw new UsernameNotFoundException("Invalid Username or password");
		}
	}

}
