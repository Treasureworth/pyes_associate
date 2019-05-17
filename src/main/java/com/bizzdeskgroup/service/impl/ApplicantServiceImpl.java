package com.bizzdeskgroup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizzdeskgroup.constants.Constants;
import com.bizzdeskgroup.dto.ServerResponse;
import com.bizzdeskgroup.model.ApplicantRecord;
import com.bizzdeskgroup.repository.ApplicantRecordRepository;
import com.bizzdeskgroup.service.ApplicantService;

@Service
@Transactional
public class ApplicantServiceImpl implements ApplicantService{

	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private ApplicantRecordRepository applicantRecordRepository;

	@Override
	public ResponseEntity<ServerResponse> getRecord(String record) {
		
		ApplicantRecord applicantRecord = new ApplicantRecord();
		
		applicantRecord = applicantRecordRepository.findByPhoneNumber(record);
		
		response.setData(applicantRecord);
		response.setStatus(Constants.OK);
		response.setMessage("Successfully fetched");
		response.setSuccess(true);	
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		
	}

}
