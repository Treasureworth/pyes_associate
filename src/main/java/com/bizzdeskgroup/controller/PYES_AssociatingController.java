package com.bizzdeskgroup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bizzdeskgroup.dto.ServerResponse;
import com.bizzdeskgroup.model.ApplicantRecord;
import com.bizzdeskgroup.service.ApplicantRecordService;
import com.bizzdeskgroup.service.LgasService;
import com.bizzdeskgroup.service.StatesService;

@CrossOrigin(origins = "*", maxAge = 86400)
@RestController
@RequestMapping("/applicant")
public class PYES_AssociatingController {

	@Autowired
    private StatesService statesService;
	
	@Autowired
	private LgasService lgasService;
	
	@Autowired
	private ApplicantRecordService applicantRecordService;
	
	
	ServerResponse response = new ServerResponse();
	
	
	@RequestMapping(value="/getallstates", method = RequestMethod.GET)
    public ResponseEntity<ServerResponse> listAllStates(){

        	return statesService.findAllStates();
    }
	
	@RequestMapping(value="/getstatebyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<ServerResponse> findStateByName(@PathVariable("name") String name){
		
		return statesService.findName(name);
	}
	
	@RequestMapping(value="/getalllgas", method = RequestMethod.GET)
	public ResponseEntity<ServerResponse> listAllLgas(){
		
		return lgasService.findAllLocalGovernment();
	}
	
	@RequestMapping(value="/getlgabyname/{name}", method = RequestMethod.GET)
	public ResponseEntity<ServerResponse> findLgaByName(@PathVariable("name") String name){
		
		return lgasService.findLocalName(name);
	}
	
	@RequestMapping(value="/updateapplicant", method = RequestMethod.POST)
	public ResponseEntity<ServerResponse> updateApplicant(@RequestBody ApplicantRecord record){
		
		return applicantRecordService.updateApplicantRecord(record);
	}
	
	@RequestMapping(value="/findapplicantbyphone/{phonenumber}", method = RequestMethod.GET)
	public ResponseEntity<ServerResponse> findApplicantByPhoneNumber(@PathVariable("phonenumber") String phonenumber){
		
			return applicantRecordService.getApplicantRecord(phonenumber);
//			return applicantService.getRecord(phonenumber);
	}
}
