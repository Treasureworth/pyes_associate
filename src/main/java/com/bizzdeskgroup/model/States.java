package com.bizzdeskgroup.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class States {
	
	@Id
	@JsonIgnore
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "state_id", nullable = false, unique = true)
	private int stateId;
	
	@Column
	private String name;
	
	@JsonManagedReference
	@OneToMany(mappedBy = "states", cascade = CascadeType.ALL)
	private Set<Lgas> lgas;

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Lgas> getLgas() {
		return lgas;
	}

	public void setLgas(Set<Lgas> lgas) {
		this.lgas = lgas;
	}
}
