package com.galaxe.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Location {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String city;
	private String state;
	private String country;
	private String postalcode;
	
	@JsonIgnore
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL)
	private List<Doctor> doctors;
	
	
	@JsonIgnore
	@OneToMany(mappedBy="location",cascade=CascadeType.ALL)
	private List<Appointment> appointments;
}
