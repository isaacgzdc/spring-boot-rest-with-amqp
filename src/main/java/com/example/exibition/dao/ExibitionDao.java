package com.example.exibition.dao;

import java.util.List;

import com.example.exibition.dto.ExibitionDTO;
import com.example.exibition.model.Exibition;

public interface ExibitionDao {

	List<Exibition> findAll(ExibitionDTO dto);
}
