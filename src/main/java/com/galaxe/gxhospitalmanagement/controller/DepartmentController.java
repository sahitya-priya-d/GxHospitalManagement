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

import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;
import com.galaxe.gxhospitalmanagement.service.DepartmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("api/GxHospital/Department")
@RestController
public class DepartmentController {


    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/all")
    public List<DepartmentDto> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{deptId}")
    public Optional<DepartmentDto> getDepartmentById(@PathVariable Long deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    @PostMapping("/add")
    public DepartmentDto saveDepartment(@RequestBody DepartmentDto departmentDTO) {
        return departmentService.saveDepartment(departmentDTO);
    }

    @DeleteMapping("/{deptId}")
    public void deleteDepartment(@PathVariable Long deptId) {
        departmentService.deleteDepartment(deptId);
    }
    @GetMapping("/byName/{name}")
    public List<DepartmentDto> getDepartmentsByName(@PathVariable String name) {
        return departmentService.getDepartmentsByName(name);
    }
}
