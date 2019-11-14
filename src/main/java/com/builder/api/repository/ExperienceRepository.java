package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.builder.api.model.Experience;


@Repository
public interface ExperienceRepository extends JpaRepository<Experience, String> {

}
