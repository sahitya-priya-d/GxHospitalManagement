package com.galaxe.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.galaxe.dto.PatientDetailsDto;
import com.galaxe.dto.PatientDto;
import com.galaxe.entites.Patient;
import com.galaxe.entites.User;
import com.galaxe.service.PatientService;



@RestController
@CrossOrigin(origins="*")
@RequestMapping("/patient")
public class PatientController {
	
	@Autowired
	private PatientService PatientService;
	
	 @PostMapping("/savepatientdetails")
	
	   public ResponseEntity<Patient> savePatientDetails(@RequestBody PatientDto patientdetailsdto ) {
	       try {
	    	   
	    	  Patient patientdetails= PatientService.savedetails(patientdetailsdto);
	    	   return new ResponseEntity<Patient>(patientdetails,HttpStatus.CREATED);
	       }catch(Exception e) {
	    	   return new ResponseEntity<Patient>(HttpStatus.INTERNAL_SERVER_ERROR);
	       }
	       
	   }
	 
	 @GetMapping("/patient/details")
	    public PatientDetailsDto findDetailsByPatientName(@RequestParam String patientName) {
	        return PatientService.findDetailsByPatientName(patientName);
	    }

}
