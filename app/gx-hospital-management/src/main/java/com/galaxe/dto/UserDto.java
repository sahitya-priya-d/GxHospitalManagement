package com.galaxe.dto;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
	@Email(message="invalid email")
	private String email;
	@NotBlank(message="please enter username")
	private String username;
	@NotNull(message="password is required")
	@NotBlank(message="password is required")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",message="invalid password")
	private String password;
	private byte[] imageUrl;
	private LocalDate dob;

}
