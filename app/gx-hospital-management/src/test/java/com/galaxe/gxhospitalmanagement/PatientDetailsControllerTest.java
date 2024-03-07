package com.galaxe.gxhospitalmanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.galaxe.controller.PatientController;
import com.galaxe.dto.PatientDto;
import com.galaxe.entites.Patient;
import com.galaxe.service.PatientService;

@ExtendWith(MockitoExtension.class)
public class PatientDetailsControllerTest {
	@Mock
	private  PatientService patientDetailsService;
	
	@InjectMocks
	private PatientController controller;
	
//	 @Test
//	    public void testSavePatientDetails_Success() {
//	       
//	        PatientDto patientDetailsDto = new PatientDto("Udaykumar",23,"6479243546","Male");
//	       
//
//	      
//	        Patient patientDetails = new Patient(2L,"Udaykumar",23,"6479243546","Male");
//	     
//
//	        
//	        when(patientDetailsService.savedetails(patientDetailsDto)).thenReturn(patientDetails);
//System.out.println(patientDetails);
//System.out.println(patientDetailsDto);
//	      
//	      
//	        ResponseEntity<Patient> response = controller.savePatientDetails(patientDetailsDto);
//
//	    System.out.println(response.getBody());
//	        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//	        assertEquals(patientDetails, response.getBody());
//	    }
//
//	    @Test
//	    public void testSavePatientDetails_Exception() {
//	        
//	        PatientDto patientDetailsDto = new PatientDto("Udaykumar",23,"6479243546","Male");
//
//
//	       
//	        when(patientDetailsService.savedetails(patientDetailsDto)).thenThrow(new RuntimeException());
//
//	       
//	       
//	        ResponseEntity<Patient> response = controller.savePatientDetails(patientDetailsDto);
//
//	     System.out.println(response.getStatusCode());
//	        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
//	    }

}
