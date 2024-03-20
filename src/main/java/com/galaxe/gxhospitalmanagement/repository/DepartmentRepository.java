package com.galaxe.gxhospitalmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long>{

	 @Query(value = "SELECT * FROM Department d WHERE LOWER(d.dept_name) LIKE LOWER(concat('%', :name, '%'))", nativeQuery = true)
	    List<Department> findByDeptNameIgnoreCaseContainingOrderByDeptName(@Param("name") String name);
}
