package com.galaxe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Patient;
@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

	List<Appointment> findByPatient_Name(String patientName);

//	List<Appointment> findByPatient(Patient patient);

}
