package com.galaxe.gxhospitalmanagement.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientsDTO {

	private Long patientId;
	private String fname;
	private String lname;
	private String gender;
	private LocalDate appointmentDate;
	private DoctorDto doctor;
	private UsersDto user;
	private AppointmentDTO appointment;
}
