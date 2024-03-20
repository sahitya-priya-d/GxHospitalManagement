package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.dto.AppointmentDTO;
import com.galaxe.gxhospitalmanagement.dto.DepartmentDto;
import com.galaxe.gxhospitalmanagement.entity.Appointments;
import com.galaxe.gxhospitalmanagement.entity.Department;
import com.galaxe.gxhospitalmanagement.mapper.EntityDtoConverter;
import com.galaxe.gxhospitalmanagement.repository.AppointmentRepository;

@Service
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	  private AppointmentRepository appointmentRepository;
    @Autowired
    private EntityDtoConverter entityDTOConverter;
	   
        @Override
	    public AppointmentDTO addAppointment(AppointmentDTO appointmentDto) {
        	Appointments appointment=entityDTOConverter.convertAppointmentDtoToEntity(appointmentDto);
	       return entityDTOConverter.convertAppointmentToDTO(appointmentRepository.save(appointment)) ;
	       
	    }

        @Override
	    public List<AppointmentDTO> getAllAppointments() {
        	List<Appointments> appointments=appointmentRepository.findAll();
        	return appointments.stream().map(entityDTOConverter::convertAppointmentToDTO).collect(Collectors.toList());
	        
	    }
        
}
