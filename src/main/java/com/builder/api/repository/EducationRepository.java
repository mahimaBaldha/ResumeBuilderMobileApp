package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builder.api.model.Education;

@Repository
public interface EducationRepository extends JpaRepository<Education, Integer> {

}
