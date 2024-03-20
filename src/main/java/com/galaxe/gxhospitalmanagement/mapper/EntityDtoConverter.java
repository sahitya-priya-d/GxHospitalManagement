package com.galaxe.gxhospitalmanagement.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.galaxe.gxhospitalmanagement.dto.AppointmentDTO;
import com.galaxe.gxhospitalmanagement.dto.BranchDTO;
import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;
import com.galaxe.gxhospitalmanagement.dto.DoctorDto;
import com.galaxe.gxhospitalmanagement.dto.LocationDTO;
import com.galaxe.gxhospitalmanagement.dto.PatientsDTO;
import com.galaxe.gxhospitalmanagement.entity.Appointments;
import com.galaxe.gxhospitalmanagement.entity.Branch;
import com.galaxe.gxhospitalmanagement.entity.Department;
import com.galaxe.gxhospitalmanagement.entity.Doctors;
import com.galaxe.gxhospitalmanagement.entity.Location;
import com.galaxe.gxhospitalmanagement.entity.Patients;

@Component
public class EntityDtoConverter {

    public DepartmentDto departmentToDepartmentDTO(Department department) {
        DepartmentDto departmentDTO = new DepartmentDto();
        departmentDTO.setDeptId(department.getDeptId());
        departmentDTO.setDeptName(department.getDeptName());
        departmentDTO.setImage(department.getImage());
        return departmentDTO;
    }

    public DoctorDto doctorToDoctorDTO(Doctors doctor) {
        DoctorDto doctorDTO = new DoctorDto();
        doctorDTO.setDoctorId(doctor.getDoctorId());
        doctorDTO.setDoctorName(doctor.getDoctorName());
        doctorDTO.setImage(doctor.getImage());
        doctorDTO.setExperience(doctor.getExperience());
        doctorDTO.setFee(doctor.getFee());
        doctorDTO.setDepartment(departmentToDepartmentDTO(doctor.getDepartment()));
        doctorDTO.setBranch(convertBranchToDTO(doctor.getBranch()));
        return doctorDTO;
    }

    public Department departmentDTOToDepartment(DepartmentDto departmentDTO) {
        Department department = new Department();
        department.setDeptId(departmentDTO.getDeptId());
        department.setDeptName(departmentDTO.getDeptName());
        department.setImage(departmentDTO.getImage());
        return department;
    }

    public Doctors doctorDTOToDoctor(DoctorDto doctorDTO) {
        Doctors doctor = new Doctors();
        doctor.setDoctorId(doctorDTO.getDoctorId());
        doctor.setDoctorName(doctorDTO.getDoctorName());
        doctor.setImage(doctorDTO.getImage());
        doctor.setExperience(doctorDTO.getExperience());
        doctor.setFee(doctorDTO.getFee());
        doctor.setDepartment(departmentDTOToDepartment(doctorDTO.getDepartment()));
        doctor.setBranch(convertBranchToEntity(doctorDTO.getBranch()));
        return doctor;
    }

    public Branch convertBranchToEntity(BranchDTO branchDTO) {
        if (branchDTO == null || branchDTO.getLocation() == null) {
            return null; 
        }
        Branch branch = new Branch();
        branch.setBranchId(branchDTO.getBranchId());
        branch.setBranchName(branchDTO.getBranchName());
        branch.setImg(branchDTO.getImg());
        branch.setAddressUrl(branchDTO.getAddressUrl());
        branch.setBranchAddress(branchDTO.getBranchAddress());
        Location location = convertLocationToEntity(branchDTO.getLocation());
        branch.setLocation(location);
        return branch;
    }

    public Location convertLocationToEntity(LocationDTO locationDTO) {
        Location location = new Location();
        location.setLocationId(locationDTO.getLocationId());
        location.setLocationName(locationDTO.getLocationName());
        return location;
    }

    public LocationDTO convertLocationToDTO(Location location) {
        LocationDTO locationDTO = new LocationDTO();
        locationDTO.setLocationId(location.getLocationId());
        locationDTO.setLocationName(location.getLocationName());
        return locationDTO;
    }

    public BranchDTO convertBranchToDTO(Branch branch) {
        if (branch == null) {
            return null; 
        }
        BranchDTO branchDTO = new BranchDTO();
        branchDTO.setBranchId(branch.getBranchId());
        branchDTO.setBranchName(branch.getBranchName());
        branchDTO.setImg(branch.getImg());
        branchDTO.setAddressUrl(branch.getAddressUrl());
        branchDTO.setBranchAddress(branch.getBranchAddress());
        branchDTO.setLocation(convertLocationToDTO(branch.getLocation()));
        return branchDTO;
    }
    public List<BranchDTO> branchToBranchDTOs(List<Branch> branches) {
        return branches.stream()
                .map(this::convertBranchToDTO)
                .collect(Collectors.toList());
    }

    public List<DoctorDto> doctorsToDoctorDTOs(List<Doctors> doctors) {
        return doctors.stream()
                .map(this::doctorToDoctorDTO)
                .collect(Collectors.toList());
    }
    public AppointmentDTO convertAppointmentToDTO(Appointments appointments){
    	AppointmentDTO appointmentDTO=new AppointmentDTO();
    	appointmentDTO.setAppointmentId(appointments.getAppointmentId());
    	appointmentDTO.setAppointmentTime(appointments.getAppointmentTime());
    	return appointmentDTO;
    }
    public Appointments convertAppointmentDtoToEntity(AppointmentDTO appointmentDTO) {
    Appointments appointments=new Appointments();
    appointments.setAppointmentId(appointmentDTO.getAppointmentId());
    appointments.setAppointmentTime(appointmentDTO.getAppointmentTime());
    return appointments;
}
    public Patients convertPatientsDtoToEntity(PatientsDTO patientsDTO) {
    	Patients patients=new Patients();
    	patients.setFname(patientsDTO.getFname());
    	patients.setLname(patientsDTO.getLname());
    	patients.setGender(patientsDTO.getGender());
    	patients.setAppointmentDate(patientsDTO.getAppointmentDate());
    	Appointments appointments=convertAppointmentDtoToEntity(patientsDTO.getAppointment());
    	patients.setAppointments(appointments);
        Doctors doctors=doctorDTOToDoctor(patientsDTO.getDoctor());
        patients.setDoctor(doctors);
        return patients;
    }
    public List<PatientsDTO> patientsToPatientsDTOs(List<Patients> patients) {
        return patients.stream()
                .map(this::patientsToPatientsDTO)
                .collect(Collectors.toList());
    }
    public PatientsDTO patientsToPatientsDTO(Patients patients) {
        PatientsDTO patientsDTO = new PatientsDTO();
        patientsDTO.setPatientId(patients.getPatientId());
        patientsDTO.setFname(patients.getFname());
        patientsDTO.setLname(patients.getLname());
        patientsDTO.setGender(patients.getGender());
        patientsDTO.setAppointmentDate(patients.getAppointmentDate());
        
        AppointmentDTO appointmentDTO = convertAppointmentToDTO(patients.getAppointments());
        patientsDTO.setAppointment(appointmentDTO);
       
        DoctorDto doctorDto = doctorToDoctorDTO(patients.getDoctor());
        patientsDTO.setDoctor(doctorDto);
        
        return patientsDTO;
    }
}
