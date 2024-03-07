package com.galaxe.dto;

import java.util.List;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDetailsDto {
	 private List<Doctor> doctors;
	    private List<Appointment> appointments;
	    private List<Location> locations;

}
