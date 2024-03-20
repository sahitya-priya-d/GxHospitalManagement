package com.galaxe.gxhospitalmanagement.entity;

import java.time.LocalDate;

import com.galaxe.gxhospitalmanagement.dto.AppointmentDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Patients {

	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long patientId;
	
	private String fname;
	private String lname;
	private String gender;
	private LocalDate appointmentDate;
	
	 @ManyToOne
	    @JoinColumn(name = "user_id") 
	    private Users user;
	 
	 @ManyToOne
	 @JoinColumn(name="appointment_id")
	 private Appointments appointments;
	 
	 @ManyToOne
	 @JoinColumn(name = "doctor_id")
	 private Doctors doctor;
	
}
