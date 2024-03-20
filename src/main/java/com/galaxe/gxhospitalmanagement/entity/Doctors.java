package com.galaxe.gxhospitalmanagement.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Doctors {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long doctorId;
	private String doctorName;
	private String image;
	private Long experience;
	private Double fee;
	
	 @ManyToOne
	    @JoinColumn(name = "dept_id") 
	    private Department department;
	 @ManyToOne
	    @JoinColumn(name = "branch_id")
	    private Branch branch;
	 
	  @OneToMany(mappedBy = "doctor")
	    private List<Patients> patients;
	

}
