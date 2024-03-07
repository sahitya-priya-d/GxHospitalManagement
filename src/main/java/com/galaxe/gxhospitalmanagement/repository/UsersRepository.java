package com.galaxe.gxhospitalmanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.gxhospitalmanagement.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

	Optional<Users> findByEmailAndPassword(String email, String password);
	Users findByEmail(String email); 
//	Users findByEmail(String email);
}
