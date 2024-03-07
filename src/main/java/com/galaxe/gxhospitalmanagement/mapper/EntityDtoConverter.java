package com.galaxe.gxhospitalmanagement.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;
import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.entity.Department;
import com.galaxe.gxhospitalmanagement.entity.Doctors;

@Component
public class EntityDtoConverter {

	 public DepartmentDto departmentToDepartmentDTO(Department department) {
	        DepartmentDto departmentDTO = new DepartmentDto();
	        departmentDTO.setDeptId(department.getDeptId());
	        departmentDTO.setDeptName(department.getDeptName());
	        return departmentDTO;
	    }

	    public DoctorDto doctorToDoctorDTO(Doctors doctor) {
	        DoctorDto doctorDTO = new DoctorDto();
	        doctorDTO.setDoctorId(doctor.getDoctorId());
	        doctorDTO.setDoctorName(doctor.getDoctorName());
	        doctorDTO.setImage(doctor.getImage());
	        doctorDTO.setDepartment(departmentToDepartmentDTO(doctor.getDepartment()));
	        return doctorDTO;
	    }

	    public List<DoctorDto> doctorsToDoctorDTOs(List<Doctors> doctors) {
	        return doctors.stream()
	                .map(this::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }

	    public Department departmentDTOToDepartment(DepartmentDto departmentDTO) {
	        Department department = new Department();
	        department.setDeptId(departmentDTO.getDeptId());
	        department.setDeptName(departmentDTO.getDeptName());
	        return department;
	    }

	    public Doctors doctorDTOToDoctor(DoctorDto doctorDTO) {
	        Doctors doctor = new Doctors();
	        doctor.setDoctorId(doctorDTO.getDoctorId());
	        doctor.setDoctorName(doctorDTO.getDoctorName());
	        doctor.setImage(doctorDTO.getImage());
	        doctor.setDepartment(departmentDTOToDepartment(doctorDTO.getDepartment()));
	        return doctor;
	    }
}
