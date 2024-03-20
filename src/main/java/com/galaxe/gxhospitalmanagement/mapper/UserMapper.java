package com.galaxe.gxhospitalmanagement.mapper;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.galaxe.gxhospitalmanagement.dto.LoginRequestDto;
import com.galaxe.gxhospitalmanagement.dto.LoginResponseDto;
import com.galaxe.gxhospitalmanagement.entity.Role;
import com.galaxe.gxhospitalmanagement.entity.Users;

@Component
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    
    public UserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Maps a RegisterRequest to a User entity, applying password encoding for security.
     *
     * @param request RegisterRequest containing user details for mapping
     * @return User entity with mapped details
     */
//    public User mapToUser(RegisterRequest request) {
//        return User.builder()
//                .firstname(request.getFirstname())
//                .lastname(request.getLastname())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.USER)
//                .build();
//    }

    /**
     * Maps an AuthenticationRequest to a User entity without password encoding.
     *
     * @param request AuthenticationRequest containing user credentials for mapping
     * @return User entity with mapped credentials
     */
    public Users mapToUser(LoginRequestDto request) {
        return Users.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
    }

    /**
     * Maps a JWT token to an AuthenticationResponse.
     *
     * @param token JWT token to be included in the AuthenticationResponse
     * @return AuthenticationResponse with the provided JWT token
     */
    public LoginResponseDto mapToAuthenticationResponse(String token, Role role) {
        return LoginResponseDto.builder()
                .token(token)
                .role(role) 
                .build();
    }
}
