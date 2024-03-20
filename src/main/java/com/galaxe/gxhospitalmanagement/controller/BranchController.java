package com.galaxe.gxhospitalmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.BranchDTO;
import com.galaxe.gxhospitalmanagement.service.BranchService;

@RequestMapping("/api/gxHospital/branch")
@CrossOrigin(origins = "http://localhost:3000")
@RestController

public class BranchController {

	 @Autowired
	    private BranchService branchService;

	    @GetMapping("/byLocation/{locationId}")
	    public List<BranchDTO> getBranchesByLocation(@PathVariable Long locationId) {
	        return branchService.getAllBranchesByLocation(locationId);
	    }
        
	    @PostMapping("/add-branch")
	    public BranchDTO addBranch(@RequestBody BranchDTO branchDTO) {
	        return branchService.addBranch(branchDTO);
	    }
}
