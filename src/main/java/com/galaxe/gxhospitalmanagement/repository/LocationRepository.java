package com.galaxe.gxhospitalmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{

}
