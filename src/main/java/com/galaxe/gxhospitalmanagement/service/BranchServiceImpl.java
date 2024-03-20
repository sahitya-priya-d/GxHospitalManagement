package com.galaxe.gxhospitalmanagement.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.dto.BranchDTO;
import com.galaxe.gxhospitalmanagement.entity.Branch;
import com.galaxe.gxhospitalmanagement.entity.Location;
import com.galaxe.gxhospitalmanagement.mapper.EntityDtoConverter;
import com.galaxe.gxhospitalmanagement.repository.BranchRepository;

import jakarta.transaction.Transactional;

@Service
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private EntityDtoConverter converter;

    @Override
    public List<BranchDTO> getAllBranchesByLocation(Long locationId) {
       

        List<Branch> branches = branchRepository.findByLocation_LocationId(locationId);
        return branches.stream()
                .map(converter::convertBranchToDTO)
                .collect(Collectors.toList());
    }
    @Transactional

    @Override
    public BranchDTO addBranch(BranchDTO branchDTO) {
        Branch branch = converter.convertBranchToEntity(branchDTO);
        Branch savedBranch = branchRepository.save(branch);
        return converter.convertBranchToDTO(savedBranch);
    }

	
}
