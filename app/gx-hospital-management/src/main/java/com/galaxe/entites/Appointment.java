package com.galaxe.entites;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Appointment {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private long id;
private LocalDate appointmentDate;

@JsonIgnore
@OneToOne
@JoinColumn(name="patient_id")
private Patient patient; 

@JsonIgnore
@ManyToMany
@JoinTable(name="doctor_appointment",
joinColumns = @JoinColumn(name = "appointment_id"),
inverseJoinColumns = @JoinColumn(name = "doctor_id"))
private List<Doctor> doctors; 

@JsonIgnore
@ManyToOne
@JoinColumn(name="location_id")
private Location location;



}
