package com.bizzdeskgroup.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bizzdeskgroup.dto.ServerResponse;
import com.bizzdeskgroup.model.ApplicantRecord;

@Service
public interface ApplicantRecordService {

	 ResponseEntity<ServerResponse> updateApplicantRecord(ApplicantRecord record);
	 
	 ResponseEntity<ServerResponse> getApplicantRecord(String record);
}
