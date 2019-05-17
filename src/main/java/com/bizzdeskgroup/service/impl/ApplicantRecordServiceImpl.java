package com.bizzdeskgroup.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bizzdeskgroup.constants.Constants;
import com.bizzdeskgroup.dto.ServerResponse;
import com.bizzdeskgroup.model.ApplicantRecord;
import com.bizzdeskgroup.model.Lgas;
import com.bizzdeskgroup.model.States;
import com.bizzdeskgroup.repository.ApplicantRecordRepository;
import com.bizzdeskgroup.repository.LgasRepository;
import com.bizzdeskgroup.repository.StatesRepository;
import com.bizzdeskgroup.service.ApplicantRecordService;

@Service
@Transactional
public class ApplicantRecordServiceImpl implements  ApplicantRecordService {

	private HttpHeaders responseHeaders = new HttpHeaders();
	
	ServerResponse response = new ServerResponse();
	
	@Autowired
	private ApplicantRecordRepository applicantRecordRepository;
	
	@Autowired
	private StatesRepository stateRepository;
	
	@Autowired
	private LgasRepository lgaRepository;
	
	@Override
	public ResponseEntity<ServerResponse> updateApplicantRecord(ApplicantRecord record) {
		
		if (record == null ) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter a user record");
			response.setSuccess(false);	
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	     } 
		
		try {
			if(!(Constants.isValidEmail(record.getEmails())) || !(Constants.isValidPhone(record.getPhoneNumber())) ) {
				response.setData("");
				response.setStatus(Constants.BAD_REQUEST);
				response.setMessage("The Email or Phone Number is not valid");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
			}
			
			ApplicantRecord applicant = new ApplicantRecord();
			applicant = applicantRecordRepository.findByPhoneNumberOrEmailsIgnoreCase(record.getPhoneNumber(), record.getEmails());

			if (applicant == null) {
				response.setData("");
				response.setStatus(Constants.OK);
				response.setMessage("Your application was not pre-selected.");
				response.setSuccess(false);
			} else if (applicant.isUpdateStatus()) {
				response.setData("");
				response.setStatus(Constants.OK);
				response.setMessage("You have already confirmed your record.");
				response.setSuccess(false);
			}
			else {
				States state = new States();
				state = stateRepository.findByName(record.getState());
				Lgas lga = new Lgas();
				lga = lgaRepository.findByLocalName(record.getLga());
				
				if (state == null) {
					response.setData("");
					response.setStatus(Constants.OK);
					response.setMessage("Sorry, the state you selected is invalid");
					response.setSuccess(false);
				} else if (lga == null) {
					response.setData("");
					response.setStatus(Constants.OK);
					response.setMessage("Sorry, the LGA you selected is invalid");
					response.setSuccess(false);
				} else {
					applicant.setUpdateStatus(true);
					applicantRecordRepository.save(applicant);
					
					response.setData(applicant);
					response.setStatus(Constants.OK);
					response.setMessage("Your LGA record has been confirmed");
					response.setSuccess(true);
				}
				
				
			}
		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("An unexpected error occured, please try again.");
			response.setSuccess(false);	
		}
		return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	}

	@Override
	public ResponseEntity<ServerResponse> getApplicantRecord(String record) {
		
		if (record == null ) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("Please enter either email or phone number");
			response.setSuccess(false);	
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));
	     } 
		
		try {
			ApplicantRecord applicant = new ApplicantRecord();
			applicant = applicantRecordRepository.findByPhoneNumberOrEmailsIgnoreCase(record, record);

			if (applicant == null) {
				response.setData("");
				response.setStatus(Constants.OK);
				response.setMessage("Your application was not pre-selected.");
				response.setSuccess(false);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			} else {
				response.setData(applicant);
				response.setStatus(Constants.OK);
				response.setMessage("Applicant record found");
				response.setSuccess(true);
				return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

			}
		} catch (Exception e) {
			response.setData("");
			response.setStatus(Constants.FAIL);
			response.setMessage("An unexpected error occured, please try again later");
			response.setSuccess(false);
			return new ResponseEntity<ServerResponse>(response, responseHeaders, ServerResponse.getStatus(response.getStatus()));

		}

	}

}
