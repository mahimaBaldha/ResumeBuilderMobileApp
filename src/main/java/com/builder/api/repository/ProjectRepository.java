package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builder.api.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Integer>{

}
