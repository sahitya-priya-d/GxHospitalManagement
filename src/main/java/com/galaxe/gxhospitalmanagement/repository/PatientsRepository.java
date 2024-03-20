package com.galaxe.gxhospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Patients;

@Repository
public interface PatientsRepository extends JpaRepository<Patients, Long>{

}
