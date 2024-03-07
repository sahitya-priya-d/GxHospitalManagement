package com.galaxe.gxhospitalmanagement.mapper;

import com.galaxe.gxhospitalmanagement.dto.UsersDto;
import com.galaxe.gxhospitalmanagement.entity.Users;

public class UsersMapper {
	public static UsersDto toDto(Users user) {
        UsersDto usersDto = new UsersDto();
        usersDto.setEmail(user.getEmail());
        usersDto.setPassword(user.getPassword());
        return usersDto;
    }
	 public static Users toEntity(UsersDto usersDto) {
	        Users user = new Users();
	        user.setEmail(usersDto.getEmail());
	        user.setPassword(usersDto.getPassword());
	        return user;
	    }
}
