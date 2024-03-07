package com.galaxe.gxhospitalmanagement.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.gxhospitalmanagement.dto.LoginRequestDto;
import com.galaxe.gxhospitalmanagement.dto.LoginResponseDto;
import com.galaxe.gxhospitalmanagement.dto.PasswordUpdateDto;
import com.galaxe.gxhospitalmanagement.dto.UsersDto;
import com.galaxe.gxhospitalmanagement.entity.Users;
import com.galaxe.gxhospitalmanagement.mapper.UsersMapper;
import com.galaxe.gxhospitalmanagement.service.UsersService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("api/gxHospital/users")
@CrossOrigin(origins = "http://localhost:3000")

public class UsersController {

	@Autowired
	UsersService usersService;

	@PostMapping("/send-reset-password")
	public ResponseEntity<?> sendResetPasswordEmail(@RequestParam String email) {
		try {
			usersService.findUserByEmail(email);
			return ResponseEntity.ok("Email sent successfully");
		} catch (Exception e) {
			return ResponseEntity.status(404).body(e.getMessage());
		}

	}

	@PostMapping("/updatepassword")
	public ResponseEntity<?> updatePassword(@RequestParam Long userId,
			@Valid @RequestBody PasswordUpdateDto passwordUpdateDTO) {
		try {
			usersService.updatePassword(userId, passwordUpdateDTO);
			return ResponseEntity.ok("Password updated successfully");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		} catch (Exception e) {
			return ResponseEntity.status(500).body(e.getMessage());
		}
	}

	@PostMapping("/authenticate")
	public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginRequestDto request) throws Exception {
		try {
			LoginResponseDto response = usersService.authenticate(request);
			return ResponseEntity.ok(response);
		} catch (BadCredentialsException e) {
			throw new BadCredentialsException("Invalid credentials !");
		} catch (UsernameNotFoundException e) {
			throw new UsernameNotFoundException("User not found !");
		} catch (InternalAuthenticationServiceException e) {
			throw new InternalAuthenticationServiceException(
					"Internal authentication service error. Please try again later.");
		} catch (Exception e) {
			throw new Exception("Internal servor Error !");
		}
	}

}
