package com.bizzdeskgroup.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.bizzdeskgroup.model.Lgas;



public interface LgasRepository extends CrudRepository<Lgas, Integer>{

	Lgas findByLocalName(String name);
	
	Collection<Lgas> findAll();
}
