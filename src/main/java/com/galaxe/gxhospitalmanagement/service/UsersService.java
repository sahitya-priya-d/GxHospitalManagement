package com.galaxe.gxhospitalmanagement.service;

import java.util.Optional;

import com.galaxe.gxhospitalmanagement.dto.LoginRequestDto;
import com.galaxe.gxhospitalmanagement.dto.LoginResponseDto;
import com.galaxe.gxhospitalmanagement.dto.PasswordUpdateDto;
import com.galaxe.gxhospitalmanagement.dto.UsersDto;
import com.galaxe.gxhospitalmanagement.entity.Users;

public interface UsersService {


	String findUserByEmail(String email);

	void updatePassword(Long userId, PasswordUpdateDto passwordUpdateDTO);

	LoginResponseDto authenticate(LoginRequestDto request);

	   

		
}
