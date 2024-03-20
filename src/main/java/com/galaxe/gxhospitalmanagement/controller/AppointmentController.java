package com.galaxe.gxhospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.AppointmentDTO;
import com.galaxe.gxhospitalmanagement.service.AppointmentService;


@RequestMapping("/api/gxHospital/appointments")
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class AppointmentController {

	@Autowired 
	private AppointmentService appointmentService;
	
	@PostMapping("/add")
	public String addAppointment(@RequestBody AppointmentDTO appointmentDTO)
	{
		appointmentService.addAppointment(appointmentDTO);
		return "Appointment added successfully";
	}
	@GetMapping("/getAll")
	public List<AppointmentDTO> getAllAppointment(){
		return appointmentService.getAllAppointments();
	}
}
