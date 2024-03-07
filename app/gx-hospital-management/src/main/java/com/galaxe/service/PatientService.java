package com.galaxe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.dto.PatientDetailsDto;
import com.galaxe.dto.PatientDto;
import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;
import com.galaxe.entites.Patient;
import com.galaxe.entites.User;
import com.galaxe.exceptions.PatientDetailsAlreadyExistsException;
import com.galaxe.exceptions.PatientNotFoundException;
import com.galaxe.exceptions.UserAlreadyExistsException;
import com.galaxe.repositories.AppointmentRepo;
import com.galaxe.repositories.DoctorRepo;
import com.galaxe.repositories.LocationRepo;
import com.galaxe.repositories.PatientRepo;

@Service
public class PatientService {
	
	@Autowired
	private PatientRepo patientrepo;
	@Autowired
	private DoctorRepo doctorrepo;
	
	@Autowired
	private LocationRepo locationrepository;
	
	@Autowired
    private AppointmentRepo appointmentRepository;
  
	
	public Patient savedetails(PatientDto patientdetailsdto) {
		try {
			
			Optional<Patient> Patientname = patientrepo.findByName(patientdetailsdto.getName());
			Optional<Patient> Patientmobilenumber = patientrepo.findByPhnumber(patientdetailsdto.getPhnumber());
			
	        if ((Patientname.isPresent() && Patientmobilenumber.isPresent()) || Patientmobilenumber.isPresent()) {
	            throw new PatientDetailsAlreadyExistsException("Patient Details already exists");
	        }else {
			
			
			Patient patientdetails=new Patient();
			patientdetails.setName(patientdetailsdto.getName());
			patientdetails.setAge(patientdetailsdto.getAge());
			patientdetails.setGender(patientdetailsdto.getGender());
			patientdetails.setPhnumber(patientdetailsdto.getPhnumber());
			
			return patientrepo.save(patientdetails);}
		}catch(Exception e) {
			
			throw new RuntimeException("error had occured");
		}
	}
	
	
	 public PatientDetailsDto findDetailsByPatientName(String patientName) {
	        List<Doctor> doctors = doctorrepo.findByAppointments_Patient_Name(patientName);
	        List<Appointment> appointments = appointmentRepository.findByPatient_Name(patientName);
	        List<Location> locations = locationrepository.findByAppointments_Patient_Name(patientName);

	        PatientDetailsDto patientDetailsDTO = new PatientDetailsDto();
	        patientDetailsDTO.setDoctors(doctors);
	        patientDetailsDTO.setAppointments(appointments);
	        patientDetailsDTO.setLocations(locations);

	        return patientDetailsDTO;
	    }

}
