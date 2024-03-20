package com.galaxe.gxhospitalmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.exception.DoctorNotFoundException;
import com.galaxe.gxhospitalmanagement.service.DoctorsService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/gxHospital/doctors")
@RestController
public class DoctorsController {

	@Autowired
	private DoctorsService doctorService;

	@GetMapping("/get-all")
	public List<DoctorDto> getAllDoctors() {
		return doctorService.getAllDoctors();
	}

	@GetMapping("/get/{doctorId}")
	public Optional<DoctorDto> getDoctorById(@PathVariable Long doctorId) {
		return doctorService.getDoctorById(doctorId);
	}

	@PostMapping("/add-doctor")
	public DoctorDto saveDoctor(@RequestBody DoctorDto doctorDTO) {
		return doctorService.saveDoctor(doctorDTO);
	}

	@DeleteMapping("/delete/{doctorId}")
	public void deleteDoctor(@PathVariable Long doctorId) {
		doctorService.deleteDoctor(doctorId);
	}

	@GetMapping("/get-by-dept/{deptId}")
	public List<DoctorDto> getDoctorsByDepartment(@PathVariable Long deptId) {
		return doctorService.getAllDoctorsByDepartmentId(deptId);
	}

	@GetMapping("/get-by-branch/{branchId}")
	public List<DoctorDto> getDoctorsByBranch(@PathVariable Long branchId) {
		return doctorService.getAllDoctorsByBranchId(branchId);
	}

	@GetMapping("/search")
	public ResponseEntity<List<DoctorDto>> searchDoctors(@RequestParam(required = false) String doctorName,
			@RequestParam(required = false) String deptName, @RequestParam(required = false) Long branchId) {

		List<DoctorDto> matchingDoctors = doctorService.searchDoctors(doctorName, deptName, branchId);

		if (matchingDoctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(matchingDoctors, HttpStatus.OK);
	}

	@GetMapping("/searchByDeptName")
	public List<DoctorDto> getDoctorByDeptName(@RequestParam(required = false) String deptName,
			@RequestParam(required = false) Long branchId) {
		return doctorService.getDoctorsByDepartmentName(deptName, branchId);
	}

	@GetMapping("/searchByNameAndDept")
	public ResponseEntity<List<DoctorDto>> searchDoctorsByNameAndDeptName(
			@RequestParam(required = true) String doctorName, @RequestParam(required = true) String deptName,
			@RequestParam(required = true) Long branchId) {

		List<DoctorDto> matchingDoctors = doctorService.searchByDoctorNameAndDeptName(doctorName, deptName, branchId);

		if (matchingDoctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(matchingDoctors, HttpStatus.OK);
	}

	@GetMapping("/searchByExperience")
	public ResponseEntity<List<DoctorDto>> searchDoctorsByExperience(@RequestParam(required = true) Long experience,
			@RequestParam(required = false) String deptName, @RequestParam(required = true) Long branchId) {

		List<DoctorDto> matchingDoctors = doctorService.getDoctorByExpAndDept(experience, deptName, branchId);

		if (matchingDoctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<>(matchingDoctors, HttpStatus.OK);
	}

	@DeleteMapping("/deleteDoctor/doctorId")
	public String delete(@PathVariable Long doctorId) {
		doctorService.deleteDoctorById(doctorId);
		return "doctor deleted successfully";
	}

	@GetMapping("/getByDept")
	public ResponseEntity<List<DoctorDto>> searchByDeptName(@RequestParam(required = true) String deptName) {
		List<DoctorDto> doctors = doctorService.findDoctorByDepartmentName(deptName);
		if (doctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	@GetMapping("/getByLocation")
	public ResponseEntity<List<DoctorDto>> findByLocation(@RequestParam(required = true) String locationName){
		List<DoctorDto> doctors = doctorService.findByLocation(locationName);
		if (doctors.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(doctors, HttpStatus.OK);
	}
	 @PutMapping("/update")
	    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto updatedDoctorDto) {
	        
	            DoctorDto updatedDoctor = doctorService.updateDoctor(updatedDoctorDto);
	            return ResponseEntity.ok(updatedDoctor);
	 }
}
