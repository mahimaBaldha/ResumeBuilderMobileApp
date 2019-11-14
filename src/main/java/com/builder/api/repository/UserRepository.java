package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builder.api.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

//	public Users findById(String id);
}
