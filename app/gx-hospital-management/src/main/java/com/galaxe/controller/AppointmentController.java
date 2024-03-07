package com.galaxe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.AppointmentRequest;
import com.galaxe.service.AppointmentService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/appointment")
public class AppointmentController {

	 @Autowired
	    private AppointmentService appointmentService;
	 
	 
	 @PostMapping("/book-appointment")
	    public Appointment bookAppointment(@RequestBody AppointmentRequest request) {
	        return appointmentService.bookAppointment(request.getDoctorId(), request.getPatientId(), request.getLocationId(), request.getAppointmentDate());
	    }

	 @DeleteMapping("/appointments/{appointmentId}")
	 public void deleteAppointment(@PathVariable long appointmentId) {
	     appointmentService.deleteAppointment(appointmentId);
	 }

	 // For updating an appointment
	 @PutMapping("/appointments/{appointmentId}")
	 public Appointment updateAppointment(@PathVariable long appointmentId, @RequestBody UpdateAppointmentRequest request) {
	     return appointmentService.updateAppointment(appointmentId, request.getNewAppointmentDate());
	 }
}
