package com.galaxe.controller;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAppointmentRequest {
	private LocalDate newAppointmentDate;
}
