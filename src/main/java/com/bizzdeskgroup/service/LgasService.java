package com.bizzdeskgroup.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bizzdeskgroup.dto.ServerResponse;

@Service
public interface LgasService {

	 ResponseEntity<ServerResponse> findLocalName(String name);
	
	 ResponseEntity<ServerResponse> findAllLocalGovernment();
}
