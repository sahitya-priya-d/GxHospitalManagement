package com.galaxe.entites;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppointmentRequest {

	
	private long doctorId;
    private long patientId;
    private long locationId;
    private LocalDate appointmentDate;

}
