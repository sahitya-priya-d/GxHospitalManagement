package com.galaxe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.entites.Appointment;
import com.galaxe.entites.Doctor;
import com.galaxe.entites.Location;
import com.galaxe.exceptions.LocationNotFoundException;
import com.galaxe.repositories.LocationRepo;

@Service
public class LocationService {

	@Autowired
    private LocationRepo locationRepository;
    
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    
    public Location getLocationById(Long id) {
        return locationRepository.findById(id)
                .orElseThrow(() -> new LocationNotFoundException("Location not found"));
    }
    
    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
    
    public Location updateLocation(Long id, Location updatedLocation) {
        Location location = getLocationById(id);
        location.setName(updatedLocation.getName());
        location.setCity(updatedLocation.getCity());
        location.setState(updatedLocation.getState());
        location.setCountry(updatedLocation.getCountry());
        location.setPostalcode(updatedLocation.getPostalcode());
        return locationRepository.save(location);
    }
    
    public void deleteLocation(Long id) {
        Location location = getLocationById(id);
        locationRepository.delete(location);
    }
    
    
    public List<Location> searchLocationsByPartialName(String partialName) {
        return locationRepository.findByNameStartingWithIgnoreCase(partialName);
    }
    
    
    public List<Appointment> getAppointmentsByLocationId(Long locationId) {
        Location location = locationRepository.findById(locationId)
            .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        return location.getAppointments();
    }

    public List<Doctor> getDoctorsByLocationId(Long locationId) {
        Location location = locationRepository.findById(locationId)
            .orElseThrow(() -> new LocationNotFoundException("Location not found"));
        System.out.println(location.getDoctors());
        return location.getDoctors();
    }
}
