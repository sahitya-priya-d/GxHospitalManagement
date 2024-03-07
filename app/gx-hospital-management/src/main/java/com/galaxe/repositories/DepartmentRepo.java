package com.galaxe.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, Long> {

	List<Department> findByNameStartingWithIgnoreCase(String partialName);

}
