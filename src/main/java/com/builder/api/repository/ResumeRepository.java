package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.builder.api.model.Resume;


@Repository
public interface ResumeRepository extends JpaRepository<Resume, String>{

}