package com.galaxe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.Patient;

@Repository
public interface PatientRepo extends JpaRepository<Patient, Long>{

	Optional<Patient> findByName(String name);

	Optional<Patient> findByPhnumber(String phnumber);

}
