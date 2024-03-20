package com.galaxe.gxhospitalmanagement.dto;

import com.galaxe.gxhospitalmanagement.entity.Role;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {

    private String token;
    @Enumerated(EnumType.STRING)
    private Role role;

}
