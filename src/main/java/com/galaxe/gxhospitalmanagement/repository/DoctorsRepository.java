package com.galaxe.gxhospitalmanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Doctors;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Long> {

	List<Doctors> findAllByDepartment_DeptId(Long deptId);

}
