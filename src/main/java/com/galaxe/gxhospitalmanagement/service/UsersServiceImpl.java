package com.galaxe.gxhospitalmanagement.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.galaxe.gxhospitalmanagement.config.JwtService;
import com.galaxe.gxhospitalmanagement.dto.LoginRequestDto;
import com.galaxe.gxhospitalmanagement.dto.LoginResponseDto;
import com.galaxe.gxhospitalmanagement.dto.PasswordUpdateDto;
import com.galaxe.gxhospitalmanagement.dto.UsersDto;
import com.galaxe.gxhospitalmanagement.entity.Role;
import com.galaxe.gxhospitalmanagement.entity.Users;
import com.galaxe.gxhospitalmanagement.exception.PasswordMismatchingException;

import com.galaxe.gxhospitalmanagement.exception.PastPasswordMatchException;
import com.galaxe.gxhospitalmanagement.exception.UserAuthenticationException;
import com.galaxe.gxhospitalmanagement.exception.UserNotFoundException;
import com.galaxe.gxhospitalmanagement.mapper.UserMapper;
import com.galaxe.gxhospitalmanagement.repository.UsersRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

	@Autowired
	private final UsersRepository userRepository;
	@Autowired
	private EmailServiceImpl emailService;

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;
	private final UserMapper userMapper;
	@Autowired
	private final PasswordEncoder passwordEncoder;
//	  @Override
//	    public Optional<Users> loginUser(UsersDto usersDto) {
//	        // Authenticate a user based on email and password
//	        Optional<Users> authenticatedUser = userRepository.findByEmailAndPassword(
//	                usersDto.getEmail(),
//	                usersDto.getPassword()
//	        );
//	        if (authenticatedUser.isPresent()) {
//	            return authenticatedUser;
//	        } else {
//	            throw new UserAuthenticationException("User authentication failed");
//	        }
//	    }

	@Override
	public String findUserByEmail(String email) {
		Users user = userRepository.findByEmail(email);

		if (user != null) {
			emailService.sendResetpasswordEmail(email, user.getUserId());
			return "Reset password email sent successfully!";
		} else {
			throw new UserNotFoundException("Your email is not registered with us");
		}
	}

	@Override
	public void updatePassword(Long userId, PasswordUpdateDto passwordUpdateDTO) {
		String newPassword = passwordUpdateDTO.getNewPassword();
		String confirmPassword = passwordUpdateDTO.getConfirmPassword();

		if (!newPassword.equals(confirmPassword)) {
			throw new PasswordMismatchingException("Passwords do not match");
		}

		Optional<Users> optionalUser = userRepository.findById(userId);
		if (optionalUser.isPresent()) {
			Users user = optionalUser.get();

			String encodedPassword = passwordEncoder.encode(newPassword);
			user.setPassword(encodedPassword);

			userRepository.save(user);
		} else {
			throw new UserNotFoundException("User not found for the provided email");
		}
	}
	@Override
	public LoginResponseDto authenticate(LoginRequestDto request) {
	    try {
	        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

	        Users user = userRepository.findByEmail(request.getEmail());

	        String jwtToken = jwtService.generateToken(user);
	        
	        
	        Role userRole = user.getRole();

	        return userMapper.mapToAuthenticationResponse(jwtToken, userRole);
	    } catch (BadCredentialsException e) {
	        throw new BadCredentialsException("Invalid credentials");
	    } catch (UsernameNotFoundException | InternalAuthenticationServiceException e) {
	        throw new UsernameNotFoundException("User not found !");
	    }
	}
}
