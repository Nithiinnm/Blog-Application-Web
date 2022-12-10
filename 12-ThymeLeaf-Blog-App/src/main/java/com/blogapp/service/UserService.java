package com.blogapp.service;

import com.blogapp.dto.RegisterDto;
import com.blogapp.entity.User;

public interface UserService {

	public void saveUser(RegisterDto user);

	public User emailUnique(String email);
}
