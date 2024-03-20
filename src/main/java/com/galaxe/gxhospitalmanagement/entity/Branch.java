package com.galaxe.gxhospitalmanagement.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class Branch {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long branchId;
	    private String branchName;
	    private String img;
	    private String addressUrl;
	    private String branchAddress;
	    @ManyToOne(cascade = CascadeType.MERGE)
	    @JoinColumn(name = "location_id")
	    private Location location;
	    @OneToMany(mappedBy = "branch") 
	    private List<Doctors> doctors;
}
