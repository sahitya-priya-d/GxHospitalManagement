package com.galaxe.gxhospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Appointments;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointments, Long>{

}
