package com.galaxe.gxhospitalmanagement.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Doctors;

@Repository
public interface DoctorsRepository extends JpaRepository<Doctors,Long> {

	List<Doctors> findAllByDepartment_DeptId(Long deptId);

	List<Doctors> findAllByBranch_BranchId(Long branchId);

	 @Query(value = "SELECT * FROM Doctors d WHERE LOWER(d.doctor_name) LIKE LOWER(concat('%', :name, '%'))", nativeQuery = true)
	    List<Doctors> findByDoctorNameIgnoreCaseContainingOrderByDoctorName(@Param("name") String name);

	List<Doctors> findAllByDepartment_DeptNameIgnoreCaseContaining(String searchTerm);
	
	@Query(value = "SELECT * FROM Doctors d WHERE LOWER(d.doctor_name) LIKE LOWER(concat('%', :name, '%')) AND d.branch_id = :branchId", nativeQuery = true)
	List<Doctors> findByDoctorNameIgnoreCaseContainingAndBranchIdOrderByDoctorName(@Param("name") String name, @Param("branchId") Long branchId);
//	
////	
////	@Query(value = "SELECT d.doctor_id AS doctorId, d.doctor_name AS doctorName, " +
////	        "d.image, d.branch_id, d.dept_id, " +
////	        "dept.dept_id AS departmentId, dept.dept_name AS deptName " +
////	        "FROM Doctors d " +
////	        "JOIN Department dept ON d.dept_id = dept.dept_id " +
////	        "WHERE LOWER(dept.dept_name) LIKE LOWER(concat('%', :deptName, '%')) " +
////	        "AND d.branch_id = :branchId", nativeQuery = true)
////	List<Doctors> findAllByDepartmentAndBranchIdIgnoreCaseContaining(
////	        @Param("deptName") String deptName, @Param("branchId") Long branchId);
	List<Doctors> findByDepartment_DeptNameIgnoreCaseContainingAndBranch_BranchId(String deptName,Long branchId);
    List <Doctors> findByExperienceAndBranch_BranchId(Long experience,Long branchId);
    List<Doctors> findByBranch_Location_LocationName(String locationName);
	
}
