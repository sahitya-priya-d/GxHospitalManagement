package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.dto.BranchDTO;
import com.galaxe.gxhospitalmanagement.dto.LocationDTO;
import com.galaxe.gxhospitalmanagement.entity.Location;
import com.galaxe.gxhospitalmanagement.mapper.EntityDtoConverter;
import com.galaxe.gxhospitalmanagement.repository.BranchRepository;
import com.galaxe.gxhospitalmanagement.repository.LocationRepository;

@Service
public class LocationServiceImpl implements LocationService {

	 @Autowired
	    private LocationRepository locationRepository;

	   

	    @Autowired
	    private EntityDtoConverter converter;

	    @Override
	    public List<LocationDTO> getAllLocations() {
	        List<Location> locations = locationRepository.findAll();
	        return locations.stream()
	                .map(converter::convertLocationToDTO)
	                .collect(Collectors.toList());
	    }


	    @Override
	    public LocationDTO addLocation(LocationDTO locationDTO) {
	        Location location = converter.convertLocationToEntity(locationDTO);
	        Location savedLocation = locationRepository.save(location);
	        return converter.convertLocationToDTO(savedLocation);
	    }

	   
	
}
