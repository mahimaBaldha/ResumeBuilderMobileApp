package com.builder.api.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.builder.api.model.Resume;
import com.builder.api.model.Users;
import com.builder.api.repository.ResumeRepository;
import com.builder.api.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userrepository;
	
	@Autowired
	private ResumeRepository resumerepo;
	
	public Users saveUser(Users user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(16);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.setOnetime(true);
		user.setSession(false);
		return userrepository.save(user);
	}
	
	public Resume BuildResume(String id, Users user) {
		Resume resume = new Resume();
		resume.setId(id);
		resume.setUser_id(user.getId());
		
		return resumerepo.save(resume);
	}
	
}
