package com.blogapp.service;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogapp.dto.RegisterDto;
import com.blogapp.entity.Role;
import com.blogapp.entity.User;
import com.blogapp.repository.RoleRepository;
import com.blogapp.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void saveUser(RegisterDto userDto) {

		String name = userDto.getFirstName() + " " + userDto.getLastName();
		Role role = roleRepo.findByName("ROLE_GUEST");
			User user = new User();
			user.setName(name);
			user.setEmail(userDto.getEmail());
			user.setPassword(passwordEncoder.encode(userDto.getPassword()));
			user.setRoles(Arrays.asList(role));
			User userSaved = userRepo.save(user);
	}

	@Override
	public User emailUnique(String email) {
		User userByEmail = userRepo.findByEmail(email);
		return userByEmail;
	}

}
