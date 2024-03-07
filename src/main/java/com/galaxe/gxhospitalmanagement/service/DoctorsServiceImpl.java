package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.entity.Doctors;
import com.galaxe.gxhospitalmanagement.mapper.EntityDtoConverter;
import com.galaxe.gxhospitalmanagement.repository.DoctorsRepository;

@Service
public class DoctorsServiceImpl implements DoctorsService {
	 @Autowired
	    private DoctorsRepository doctorRepository;

	    @Autowired
	    private EntityDtoConverter entityDTOConverter;

	    @Override
	    public List<DoctorDto> getAllDoctors() {
	        List<Doctors> doctors = doctorRepository.findAll();
	        return doctors.stream()
	                .map(entityDTOConverter::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }
        @Override
	    public Optional<DoctorDto> getDoctorById(Long id) {
	        Optional<Doctors> doctor = doctorRepository.findById(id);
	        return doctor.map(entityDTOConverter::doctorToDoctorDTO);
	    }
	    @Override
	    public DoctorDto saveDoctor(DoctorDto doctorDTO) {
	        Doctors doctor = entityDTOConverter.doctorDTOToDoctor(doctorDTO);
	        Doctors savedDoctor = doctorRepository.save(doctor);
	        return entityDTOConverter.doctorToDoctorDTO(savedDoctor);
	    }

	    @Override
	    public void deleteDoctor(Long id) {
	        doctorRepository.deleteById(id);
	    }
	    
	    @Override
	    public List<DoctorDto> getAllDoctorsByDepartmentId(Long deptId) {
	        List<Doctors> doctors = doctorRepository.findAllByDepartment_DeptId(deptId);
	        return doctors.stream()
	                .map(entityDTOConverter::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }
}
