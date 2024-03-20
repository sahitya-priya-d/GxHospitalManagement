package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.Optional;

import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.entity.Doctors;

public interface DoctorsService {

	List<DoctorDto> getAllDoctors();

	DoctorDto saveDoctor(DoctorDto doctorDTO);

	void deleteDoctor(Long id);

	Optional<DoctorDto> getDoctorById(Long id);

	List<DoctorDto> getAllDoctorsByDepartmentId(Long deptId);

	List<DoctorDto> getAllDoctorsByBranchId(Long branchId);

	List<Doctors> getDoctorsByName(String name);



//	List<DoctorDto> searchDoctors(String searchTerm, Long branchId);

	List<DoctorDto> getDoctorsByDepartmentName(String deptName, Long branchId);

	List<DoctorDto> searchDoctors(String doctorName, String deptName, Long branchId);

	List<DoctorDto> searchByDoctorNameAndDeptName(String doctorName, String deptName, Long branchId);

	List<DoctorDto> getDoctorByExpAndDept(Long exp, String deptName, Long branchId);

	void deleteDoctorById(Long id);

	List<DoctorDto> findDoctorByDepartmentName(String deptName);

	List<DoctorDto> findByLocation(String locationName);

	DoctorDto updateDoctor(DoctorDto updatedDoctorDto);




}
