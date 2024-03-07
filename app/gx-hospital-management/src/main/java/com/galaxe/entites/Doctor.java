package com.galaxe.entites;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

	
public class Doctor {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String specialization;
	private double appoinmentPrice;
	private int experience;
	private String address;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="department_id")
	private Department department;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy="doctors",cascade=CascadeType.ALL)
	private List<Appointment> appointments;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="location_id")
	private Location location;
	

}
