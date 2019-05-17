package com.bizzdeskgroup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bizzdeskgroup.model.ApplicantRecord;

@Repository
public interface ApplicantRecordRepository extends CrudRepository<ApplicantRecord, Integer>{
	
	ApplicantRecord findByPhoneNumberOrEmailsIgnoreCase(String phoneNumber, String emails);
	
	ApplicantRecord findByPhoneNumber(String phoneNumber);

}
