package com.galaxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.service.DepartmentService;
import com.galaxe.service.DoctorService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/doctor")
public class DoctorController {
@Autowired
	private DoctorService doctorService;

@PostMapping("/add/{departmentId}/{locationId}")
public ResponseEntity<Doctor> addAppointment(@PathVariable Long departmentId,@PathVariable Long locationId,@RequestBody Doctor doctor) {
    Doctor savedDoctor = doctorService.createDoctor(departmentId,locationId, doctor);
    return new ResponseEntity<>(savedDoctor, HttpStatus.CREATED);
}
	
@GetMapping("/finddoctors")
public List<Doctor> findDoctorsByLocationName(@RequestParam String locationName) {
    return doctorService.findDoctorsByLocationName(locationName);
}

@GetMapping("/appointments/getbyname")
public List<Appointment> findAppointmentsByDoctorName(@RequestParam String doctorName) {
    return doctorService.findAppointmentsByDoctorName(doctorName);
}

}
