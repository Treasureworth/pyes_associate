package com.bizzdeskgroup.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;

import com.bizzdeskgroup.model.States;


public interface StatesRepository extends CrudRepository<States, Integer>{

	States findByName(String name);
	
	Collection<States> findAll();
}
