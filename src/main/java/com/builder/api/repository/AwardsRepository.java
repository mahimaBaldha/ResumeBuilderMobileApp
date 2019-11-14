package com.builder.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.builder.api.model.Awards;

@Repository
public interface AwardsRepository extends JpaRepository<Awards, String>{

}
