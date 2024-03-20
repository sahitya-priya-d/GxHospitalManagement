package com.galaxe.gxhospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.entity.Patients;
import com.galaxe.gxhospitalmanagement.repository.PatientsRepository;

@Service
public class PatientsServiceImpl implements PatientsService{

	@Autowired
    private PatientsRepository patientsRepository;
	
	@Override
	
	public void addPatient(Patients patient) {
		patientsRepository.save(patient);
	}
}
