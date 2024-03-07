package com.galaxe.entites;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
	public LoginResponse(String message, boolean status,long userId) {
		this.message=message;
		this.status=status;
		this.userId=userId;
	}
	String message;
    Boolean status;
    Long userId;
	public boolean isSuccess() {
		
		return false;
	}
}
