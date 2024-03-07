package com.galaxe.gxhospitalmanagement.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.service.DoctorsService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/GxHospital/Doctors")
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
    public List<DoctorDto> getDoctorsByDepartment(@PathVariable Long deptId)
    {
     return	doctorService.getAllDoctorsByDepartmentId(deptId);
    }
}
