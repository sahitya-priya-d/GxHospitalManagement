package com.galaxe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;
import com.galaxe.service.LocationService;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/location")

public class LocationController {

	
	 @Autowired
	    private LocationService locationService;
	    
	    @GetMapping("/getall")
	    public List<Location> getAllLocations() {
	        return locationService.getAllLocations();
	    }
	    
	    @GetMapping("/getbyid/{id}")
	    public ResponseEntity<Location> getLocationById(@PathVariable Long id) {
	        Location location = locationService.getLocationById(id);
	        return ResponseEntity.ok(location);
	    }
	    
	    @PostMapping("/createlocation")
	    public ResponseEntity<Location> createLocation(@RequestBody Location location) {
	        Location createdLocation = locationService.createLocation(location);
	        return ResponseEntity.status(HttpStatus.CREATED).body(createdLocation);
	    }
	    
	    @PutMapping("/update/{id}")
	    public ResponseEntity<Location> updateLocation(@PathVariable Long id, @RequestBody Location updatedLocation) {
	        Location location = locationService.updateLocation(id, updatedLocation);
	        return ResponseEntity.ok(location);
	    }
	    
	    @DeleteMapping("/deletebyid/{id}")
	    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
	        locationService.deleteLocation(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    
	    @GetMapping("/search")
	    public ResponseEntity<List<Location>> searchLocationsByPartialName(@RequestParam String partialName) {
	        List<Location> locations = locationService.searchLocationsByPartialName(partialName);
	        return ResponseEntity.ok(locations);
	    }
	    
	    
	    @GetMapping("/{locationId}/appointments")
	    public ResponseEntity<List<Appointment>> getAppointmentsByLocationId(@PathVariable Long locationId) {
	        return  ResponseEntity.ok(locationService.getAppointmentsByLocationId(locationId));
	    }

	    @GetMapping("/{locationId}/doctors")
	    public ResponseEntity<List<Doctor>> getDoctorsByLocationId(@PathVariable Long locationId) {
	        return ResponseEntity.ok(locationService.getDoctorsByLocationId(locationId));
	    }
}
