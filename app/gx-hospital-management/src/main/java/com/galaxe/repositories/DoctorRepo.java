package com.galaxe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;

@Repository
public interface DoctorRepo extends JpaRepository<Doctor, Long> {

	List<Doctor> findByAppointments_Patient_Name(String patientName);

	List<Doctor> findByLocation_Name(String locationName);

	Doctor findByName(String doctorName);

//	List<Doctor> findByAppointments(List<Appointment> appointments);

}
