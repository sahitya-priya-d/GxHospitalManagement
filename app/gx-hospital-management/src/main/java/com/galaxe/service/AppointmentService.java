package com.galaxe.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;
import com.galaxe.entites.Patient;
import com.galaxe.exceptions.DoctorNotFoundException;
import com.galaxe.exceptions.LocationNotFoundException;
import com.galaxe.exceptions.PatientNotFoundException;
import com.galaxe.repositories.AppointmentRepo;
import com.galaxe.repositories.DoctorRepo;
import com.galaxe.repositories.LocationRepo;
import com.galaxe.repositories.PatientRepo;

@Service
public class AppointmentService {

	
	@Autowired
    private AppointmentRepo appointmentRepository;
    
    @Autowired
    private PatientRepo patientRepository;
    
    @Autowired
    private LocationRepo locationRepository;
    

    @Autowired
    private DoctorRepo doctorRepository;
    public Appointment bookAppointment(long doctorId, long patientId,long locationId, LocalDate appointmentDate) {
        // Fetch doctor and patient from repository
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow(() -> new DoctorNotFoundException("Doctor not found"));
        Patient patient = patientRepository.findById(patientId).orElseThrow(() -> new PatientNotFoundException("Patient not found"));
        Location location = locationRepository.findById(doctorId).orElseThrow(() -> new LocationNotFoundException("location not found"));
        
        
        Appointment appointment = new Appointment();
        appointment.setAppointmentDate(appointmentDate);
        appointment.setPatient(patient);
        appointment.setLocation(location);

        // Create a list of doctors and add the fetched doctor to it
        List<Doctor> doctors = new ArrayList<>();
        doctors.add(doctor);
        appointment.setDoctors(doctors);

        // Save appointment
        return appointmentRepository.save(appointment);
    }
    
    
 // Inside AppointmentServiceImpl.java
    public void deleteAppointment(long appointmentId) {
        appointmentRepository.deleteById(appointmentId);
    }

    
    public Appointment updateAppointment(long appointmentId, LocalDate newDate) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setAppointmentDate(newDate);
        return appointmentRepository.save(appointment);
    }
}
