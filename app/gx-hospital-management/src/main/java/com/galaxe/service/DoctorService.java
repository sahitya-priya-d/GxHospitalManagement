package com.galaxe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Department;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;
import com.galaxe.entites.Patient;
import com.galaxe.exceptions.DoctorNotFoundException;
import com.galaxe.exceptions.PatientNotFoundException;
import com.galaxe.repositories.DepartmentRepo;
import com.galaxe.repositories.DoctorRepo;
import com.galaxe.repositories.LocationRepo;
import com.galaxe.repositories.PatientRepo;

@Service
public class DoctorService {
	
	@Autowired
	private DepartmentRepo departmentRepository;
	
	@Autowired
	private DoctorRepo doctorRepository;
	
	@Autowired
	private PatientRepo patientRepository;
	
	@Autowired
	private LocationRepo locationRepository;
	
	 public Doctor createDoctor(Long departmentId,Long locationId,Doctor doctor) {
	       
	        Department department = departmentRepository.findById(departmentId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));

	        Location location = locationRepository.findById(locationId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
	        
	doctor.setDepartment(department);;
	doctor.setLocation(location);
	       
	        return doctorRepository.save(doctor);
	    }
	 
	 public List<Doctor> findDoctorsByLocationName(String locationName) {
	        return doctorRepository.findByLocation_Name(locationName);
	    }
	 
	 
	 public List<Appointment> findAppointmentsByDoctorName(String doctorName) {
	        Doctor doctor = doctorRepository.findByName(doctorName);
	        if (doctor != null) {
	            return doctor.getAppointments();
	        } else {
	            // Handle case where doctor with given name is not found
	            return null;
	        }
	    }

}
