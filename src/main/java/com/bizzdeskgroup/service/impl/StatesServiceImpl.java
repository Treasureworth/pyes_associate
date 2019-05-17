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
import com.bizzdeskgroup.model.States;
import com.bizzdeskgroup.repository.StatesRepository;
import com.bizzdeskgroup.service.StatesService;


@Service
@Transactional 
public class StatesServiceImpl implements StatesService{
	
	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private StatesRepository stateRepository;
	
	@Override
	public ResponseEntity<ServerResponse> findName(String name) {
		
		if (name == null || name.isEmpty()) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter a valid state");
			response.setSuccess(false);	
	     } else {
			try {
				States state = new States();
				state = stateRepository.findByName(name);
				
				if (state != null) {
					response.setData(state);
					response.setStatus(Constants.OK);
					response.setMessage("State was found");
					response.setSuccess(true);	
			      }
				else {
					response.setData("");
					response.setStatus(Constants.FAIL);
					response.setMessage("The state you entered was not found");
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
	public ResponseEntity<ServerResponse> findAllStates() {
		try {
			Collection<States> state = new HashSet<>();
			state = stateRepository.findAll();
			
			if (state != null && state.size() > 0 ) {
				response.setData(state);
				response.setStatus(Constants.OK);
				response.setMessage("State records were fetched successfuly");
				response.setSuccess(true);	
		      } else {
		    	  response.setData("");
		    	  response.setStatus(Constants.FAIL);
		    	  response.setMessage("No state has been recorded");
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
