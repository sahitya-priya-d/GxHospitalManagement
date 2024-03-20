package com.galaxe.gxhospitalmanagement.service;

import java.util.List;

import com.galaxe.gxhospitalmanagement.dto.LocationDTO;

public interface LocationService {

	List<LocationDTO> getAllLocations();

	LocationDTO addLocation(LocationDTO locationDTO);



}
