package com.galaxe.gxhospitalmanagement.dto;

import com.galaxe.gxhospitalmanagement.entity.Location;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BranchDTO {

	   private Long branchId;
	    private String branchName;
	    private String img;
	    private String addressUrl;
	    private String branchAddress;
	    private LocationDTO location;
}
