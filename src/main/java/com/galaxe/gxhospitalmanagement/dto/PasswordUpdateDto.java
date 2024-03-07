package com.galaxe.gxhospitalmanagement.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PasswordUpdateDto {

    @NotEmpty(message = "New password cannot be empty")
    private String newPassword;

    @NotEmpty(message = "Confirm password cannot be empty")
    private String confirmPassword;
}
