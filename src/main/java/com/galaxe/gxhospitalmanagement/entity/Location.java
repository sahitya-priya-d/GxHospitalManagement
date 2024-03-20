package com.galaxe.gxhospitalmanagement.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Location {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long locationId;

	    private String locationName;

	    @OneToMany(mappedBy = "location")
	    private List<Branch> branches;
}
