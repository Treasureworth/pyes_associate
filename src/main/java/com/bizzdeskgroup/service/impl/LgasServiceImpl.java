package com.bizzdeskgroup.service.impl;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizzdeskgroup.constants.Constants;
import com.bizzdeskgroup.dto.ServerResponse;
import com.bizzdeskgroup.model.Lgas;
import com.bizzdeskgroup.repository.LgasRepository;
import com.bizzdeskgroup.service.LgasService;

@Service
@Transactional 
public class LgasServiceImpl implements LgasService{

	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private LgasRepository lgasRepository;
	
	
	@Override
	public ResponseEntity<ServerResponse> findLocalName(String name) {
		if (name == null || name.isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter a valid LGA");
			response.setSuccess(false);	
	     } else {
			try {
				Lgas lga = new Lgas();
				lga = lgasRepository.findByLocalName(name);
				
				if (lga != null) {
					response.setData(lga);
					response.setStatus(Constants.OK);
					response.setMessage("LGA was found");
					response.setSuccess(true);	
			      }
				else {
					response.setData("");
					response.setStatus(Constants.FAIL);
					response.setMessage("The LGA you entered was not found");
					response.setSuccess(false);
				}
			} catch(Exception e) {
				response.setData("");
				response.setStatus(Constants.FAIL);
				response.setMessage("An unexpected error occurred with this details: "+ e.getMessage());
				response.setSuccess(false);	
			}
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> findAllLocalGovernment() {
		try {
			Collection<Lgas> lgas = new HashSet<>();
			lgas = lgasRepository.findAll();
			
			if (lgas != null && lgas.size() > 0 ) {
				response.setData(lgas);
				response.setStatus(Constants.OK);
				response.setMessage("LGAs record fetched");
				response.setSuccess(true);	
		      } else {
		    	  response.setData("");
		    	  response.setStatus(Constants.FAIL);
		    	  response.setMessage("No LGA has been recorded");
		    	  response.setSuccess(true);
		      }
		} catch(Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("An unexpected error occurred with this details: "+ e.getMessage());
			response.setSuccess(false);	
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

}
