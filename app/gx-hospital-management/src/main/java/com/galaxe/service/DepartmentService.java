package com.galaxe.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galaxe.entites.Department;
import com.galaxe.entites.Doctor;
import com.galaxe.exceptions.DoctorNotFoundException;
import com.galaxe.repositories.DepartmentRepo;

@Service
public class DepartmentService {
	
	
	@Autowired
	private DepartmentRepo departmentrepo;
	
	 public Department createDepartment(Department department) {
	       
	        return departmentrepo.save(department);
	    }
	 
	 
	 public List<Department> searchLocationsByPartialName(String partialName) {
	        return departmentrepo.findByNameStartingWithIgnoreCase(partialName);
	    }


}
