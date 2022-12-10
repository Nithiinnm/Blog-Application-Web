package com.blogapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
 	public User findByEmail(String email);

}
