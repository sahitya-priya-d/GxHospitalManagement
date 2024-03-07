package com.galaxe.gxhospitalmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	List<Department> findByDeptNameIgnoreCaseContainingOrderByDeptName(String name);
}
