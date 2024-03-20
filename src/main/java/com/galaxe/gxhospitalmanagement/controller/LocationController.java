package com.galaxe.gxhospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.LocationDTO;
import com.galaxe.gxhospitalmanagement.service.LocationService;

@RequestMapping("/api/gxHospital/location")
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class LocationController {

	@Autowired
   private LocationService locationService;
	
	 @GetMapping("/get-all-location")
	    public List<LocationDTO> getAllLocations() {
	        return locationService.getAllLocations();
	    }


	    @PostMapping("/add-location")
	    public LocationDTO addLocation(@RequestBody LocationDTO locationDTO) {
	        return locationService.addLocation(locationDTO);
	    }

}
