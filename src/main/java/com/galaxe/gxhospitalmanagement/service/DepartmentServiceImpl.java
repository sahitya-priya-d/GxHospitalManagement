package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;
import com.galaxe.gxhospitalmanagement.entity.Department;
import com.galaxe.gxhospitalmanagement.mapper.EntityDtoConverter;
import com.galaxe.gxhospitalmanagement.repository.DepartmentRepository;

@Service
public class DepartmentServiceImpl implements DepartmentService{
	@Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EntityDtoConverter entityDTOConverter;

    @Override
    public List<DepartmentDto> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();
        return departments.stream()
                .map(entityDTOConverter::departmentToDepartmentDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DepartmentDto> getDepartmentById(Long id) {
        Optional<Department> department = departmentRepository.findById(id);
        return department.map(entityDTOConverter::departmentToDepartmentDTO);
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDTO) {
        Department department = entityDTOConverter.departmentDTOToDepartment(departmentDTO);
        Department savedDepartment = departmentRepository.save(department);
        return entityDTOConverter.departmentToDepartmentDTO(savedDepartment);
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
    @Override
    public List<DepartmentDto> getDepartmentsByName(String name) {
        List<Department> matchingDepartments = departmentRepository
                .findByDeptNameIgnoreCaseContainingOrderByDeptName(name);

        return matchingDepartments.stream()
                .map(entityDTOConverter::departmentToDepartmentDTO)
                .collect(Collectors.toList());
    }
}
