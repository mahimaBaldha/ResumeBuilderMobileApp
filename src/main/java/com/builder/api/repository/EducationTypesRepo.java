package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.builder.api.model.EduType;


@Repository
public interface EducationTypesRepo extends JpaRepository<EduType, Integer>{

}
