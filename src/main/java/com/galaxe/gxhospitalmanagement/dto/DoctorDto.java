package com.galaxe.gxhospitalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DoctorDto {
	 private Long doctorId;
	    private String doctorName;
	    private String image;
	    private DepartmentDto department;
}
