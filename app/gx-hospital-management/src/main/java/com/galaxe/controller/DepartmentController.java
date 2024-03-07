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
import com.galaxe.entites.Department;
import com.galaxe.service.DepartmentService;
import com.galaxe.service.DoctorService;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	 @PostMapping("/add")
	    public ResponseEntity<Department> addAppointment(@RequestBody Department department) {
	        Department savedDepartment = departmentService.createDepartment(department);
	        return new ResponseEntity<>(savedDepartment, HttpStatus.CREATED);
	    }
	 
	 @GetMapping("/search")
	    public ResponseEntity<List<Department>> searchLocationsByPartialName(@RequestParam String partialName) {
	        List<Department> locations = departmentService.searchLocationsByPartialName(partialName);
	        return ResponseEntity.ok(locations);
	    }
}
