package com.galaxe.gxhospitalmanagement.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {

	private Long appointmentId;
	private LocalTime appointmentTime;
}
