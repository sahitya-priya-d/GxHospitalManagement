package com.galaxe.gxhospitalmanagement.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.entity.Branch;
import com.galaxe.gxhospitalmanagement.entity.Department;
import com.galaxe.gxhospitalmanagement.entity.Doctors;
import com.galaxe.gxhospitalmanagement.exception.DoctorNotFoundException;
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
	    @Override
	    public List<DoctorDto> getAllDoctorsByBranchId(Long branchId) {
	        List<Doctors> doctors = doctorRepository.findAllByBranch_BranchId(branchId);
	        return doctors.stream()
	                .map(entityDTOConverter::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }
	    @Override
	    public List<Doctors> getDoctorsByName(String name) {
	        List<Doctors> matchingDoctors = doctorRepository
	                .findByDoctorNameIgnoreCaseContainingOrderByDoctorName(name);

	        return matchingDoctors;
	    }
	    @Override
	    public List<DoctorDto> searchDoctors(String doctorName, String deptName, Long branchId) {
	        Set<Long> uniqueDoctorIds = new HashSet<>();
	        List<Doctors> matchingDoctors = new ArrayList<>();

	        if (doctorName != null && !doctorName.isEmpty()) {
	            matchingDoctors.addAll(doctorRepository.findByDoctorNameIgnoreCaseContainingAndBranchIdOrderByDoctorName(doctorName, branchId));
	        }

	        if (deptName != null && !deptName.isEmpty()) {
	            matchingDoctors.addAll(doctorRepository.findByDepartment_DeptNameIgnoreCaseContainingAndBranch_BranchId(deptName, branchId));
	        }

	        List<Doctors> uniqueDoctors = matchingDoctors.stream()
	                .filter(doctor -> uniqueDoctorIds.add(doctor.getDoctorId()))
	                .collect(Collectors.toList());

	        return uniqueDoctors.stream()
	                .map(entityDTOConverter::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }
	    @Override
	    public List<DoctorDto> searchByDoctorNameAndDeptName(String doctorName, String deptName, Long branchId) {
	        List<Doctors> matchingDoctorsByName = new ArrayList<>();
	        List<Doctors> matchingDoctorsByDeptName = new ArrayList<>();

	        if (doctorName != null && !doctorName.isEmpty()) {
	            matchingDoctorsByName.addAll(doctorRepository.findByDoctorNameIgnoreCaseContainingAndBranchIdOrderByDoctorName(doctorName, branchId));
	        }

	        if (deptName != null && !deptName.isEmpty()) {
	            matchingDoctorsByDeptName.addAll(doctorRepository.findByDepartment_DeptNameIgnoreCaseContainingAndBranch_BranchId(deptName, branchId));
	        }

	        List<Doctors> commonDoctors = matchingDoctorsByName.stream()
	                .filter(matchingDoctorsByDeptName::contains)
	                .collect(Collectors.toList());

	        return commonDoctors.stream()
	                .map(entityDTOConverter::doctorToDoctorDTO)
	                .collect(Collectors.toList());
	    }
	    
	    @Override 
	    public List<DoctorDto> getDoctorsByDepartmentName(String deptName,Long branchId)
	    {
	    	List<Doctors>doctors=	doctorRepository.findByDepartment_DeptNameIgnoreCaseContainingAndBranch_BranchId(deptName, branchId);
	    			  return doctors.stream()
	    		                .map(entityDTOConverter::doctorToDoctorDTO)
	    		                .collect(Collectors.toList());
	    }
	    @Override
	    public List<DoctorDto> getDoctorByExpAndDept(Long exp,String deptName,Long branchId){
	    	 Set<Long> uniqueDoctorIds = new HashSet<>();
		        List<Doctors> matchingDoctors = new ArrayList<>();
		        if (deptName != null && !deptName.isEmpty()) {
		            matchingDoctors.addAll(doctorRepository.findByDepartment_DeptNameIgnoreCaseContainingAndBranch_BranchId(deptName, branchId));
		        }
		        matchingDoctors.addAll(doctorRepository.findByExperienceAndBranch_BranchId(exp, branchId));
		        List<Doctors> uniqueDoctors = matchingDoctors.stream()
		                .filter(doctor -> uniqueDoctorIds.add(doctor.getDoctorId()))
		                .collect(Collectors.toList());

		        return uniqueDoctors.stream()
		                .map(entityDTOConverter::doctorToDoctorDTO)
		                .collect(Collectors.toList());
	    	
	    }
	    @Override
	    public void deleteDoctorById(Long id) {
	        doctorRepository.deleteById(id);
	    }
	    @Override
	    public List<DoctorDto> findDoctorByDepartmentName(String deptName) {
	    	List<Doctors> doctors= doctorRepository.findAllByDepartment_DeptNameIgnoreCaseContaining(deptName);
	    	return doctors.stream().map(entityDTOConverter::doctorToDoctorDTO).collect(Collectors.toList());
	    	
	    }
	    @Override
	    public List<DoctorDto> findByLocation(String locationName){
	    	List<Doctors > doctors=doctorRepository.findByBranch_Location_LocationName(locationName);
	    	return doctors.stream().map(entityDTOConverter::doctorToDoctorDTO).collect(Collectors.toList());
	    }
	    @Override
	    public DoctorDto updateDoctor(DoctorDto updatedDoctorDto) {
	        Optional<Doctors> optionalDoctor = doctorRepository.findById(updatedDoctorDto.getDoctorId());

	        if (optionalDoctor.isPresent()) {
	            Doctors doctor = optionalDoctor.get();
	            
	            
	            doctor.setDoctorName(updatedDoctorDto.getDoctorName());
	            doctor.setImage(updatedDoctorDto.getImage());
	            doctor.setExperience(updatedDoctorDto.getExperience());
	            doctor.setFee(updatedDoctorDto.getFee());
	            
	            
	            Department department =entityDTOConverter.departmentDTOToDepartment(updatedDoctorDto.getDepartment());
	            doctor.setDepartment(department);

	            
	            Branch branch =entityDTOConverter.convertBranchToEntity(updatedDoctorDto.getBranch());
	            doctor.setBranch(branch);
	            
	            
	            Doctors updatedDoctor = doctorRepository.save(doctor);
	            
	            
	            return entityDTOConverter.doctorToDoctorDTO(updatedDoctor);
	        }
	        else {
	        	throw new DoctorNotFoundException("Doctor not found");
	        }
	    }
}
