package com.builder.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.builder.api.model.Users;
import com.builder.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepository;
	
	public Users saveUser(Users user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setOnetime(true);
		user.setSession(false);
		return userrepository.save(user);
	}
	
}
