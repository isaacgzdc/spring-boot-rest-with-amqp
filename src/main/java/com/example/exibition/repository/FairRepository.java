package com.example.exibition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.exibition.model.Fair;

@Repository
public interface FairRepository extends JpaRepository<Fair, Long>{

}
