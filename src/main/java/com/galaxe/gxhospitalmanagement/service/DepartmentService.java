package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;

public interface DepartmentService {

	void deleteDepartment(Long id);

	DepartmentDto saveDepartment(DepartmentDto departmentDTO);

	Optional<DepartmentDto> getDepartmentById(Long id);

	List<DepartmentDto> getAllDepartments();
//
//	Optional<DepartmentDto> getDepartmentByName(String name);

	List<DepartmentDto> getDepartmentsByName(String name);

}
