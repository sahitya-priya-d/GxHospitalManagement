package com.galaxe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.Location;

@Repository
public interface LocationRepo extends JpaRepository<Location, Long> {

	List<Location> findByNameStartingWithIgnoreCase(String partialName);

	List<Location> findByAppointments_Patient_Name(String patientName);

}
