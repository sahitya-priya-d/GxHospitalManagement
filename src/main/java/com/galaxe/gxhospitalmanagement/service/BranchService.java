package com.galaxe.gxhospitalmanagement.service;

import java.util.List;

import com.galaxe.gxhospitalmanagement.dto.BranchDTO;

public interface BranchService {

	 List<BranchDTO> getAllBranchesByLocation(Long locationId);
	    BranchDTO addBranch(BranchDTO branchDTO);
}
