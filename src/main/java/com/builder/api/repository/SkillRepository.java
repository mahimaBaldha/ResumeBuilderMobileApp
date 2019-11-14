package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.builder.api.model.Skill;


@Repository
public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
