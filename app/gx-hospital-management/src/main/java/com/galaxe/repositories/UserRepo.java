package com.galaxe.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galaxe.entites.User;

import java.util.List;


@Repository
public interface UserRepo extends JpaRepository<User, Long>{

	
	

	User findByUsername(String username);

	Optional<User> findOneByUsernameAndPassword(String username, String encodedPassword);

	Optional<User> findByEmail(String email);

}
