package com.galaxe.dto;

import com.galaxe.entites.Patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientDto {
private String name;
	private long age;
	private String phnumber;
	private String gender;
}
