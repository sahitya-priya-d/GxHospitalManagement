package com.galaxe.gxhospitalmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.entity.Patients;
import com.galaxe.gxhospitalmanagement.service.PatientsService;

@RequestMapping("api/gxHospital/patients")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController {

	@Autowired
	private PatientsService patientsService;
	
	@PostMapping("/add")
	public String addPatient(@RequestBody Patients patients) {
		patientsService.addPatient(patients);
		return "Patient added successfully";
	}
}
