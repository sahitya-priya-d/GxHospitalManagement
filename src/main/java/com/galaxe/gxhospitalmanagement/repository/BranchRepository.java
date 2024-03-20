package com.galaxe.gxhospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Branch;
import com.galaxe.gxhospitalmanagement.entity.Location;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Long>{
    List<Branch> findByLocation_LocationId(Long locationId);
}
