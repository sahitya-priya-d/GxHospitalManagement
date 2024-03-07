package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import com.galaxe.gxhospitalmanagement.dto.DoctorDto;

public interface DoctorsService {

	List<DoctorDto> getAllDoctors();

	DoctorDto saveDoctor(DoctorDto doctorDTO);

	void deleteDoctor(Long id);

	Optional<DoctorDto> getDoctorById(Long id);

	List<DoctorDto> getAllDoctorsByDepartmentId(Long deptId);

}
