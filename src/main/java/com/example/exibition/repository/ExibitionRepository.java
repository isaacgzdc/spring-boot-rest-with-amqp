package com.example.exibition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exibition.model.Exibition;

@Repository
public interface ExibitionRepository extends JpaRepository<Exibition, Long>{

}
