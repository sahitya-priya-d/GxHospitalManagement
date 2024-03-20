package com.galaxe.gxhospitalmanagement.service;

import java.util.List;

import com.galaxe.gxhospitalmanagement.dto.AppointmentDTO;


public interface AppointmentService {

	List<AppointmentDTO> getAllAppointments();

	AppointmentDTO addAppointment(AppointmentDTO appointmentDto);

}
