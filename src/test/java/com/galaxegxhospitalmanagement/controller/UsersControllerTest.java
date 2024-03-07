//package com.galaxegxhospitalmanagement.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.ResponseEntity;
//
//import com.galaxe.gxhospitalmanagement.controller.UsersController;
//import com.galaxe.gxhospitalmanagement.dto.PasswordUpdateDto;
//import com.galaxe.gxhospitalmanagement.dto.UsersDto;
//import com.galaxe.gxhospitalmanagement.entity.Users;
//import com.galaxe.gxhospitalmanagement.mapper.UsersMapper;
//import com.galaxe.gxhospitalmanagement.service.UsersService;
//
//@ExtendWith(MockitoExtension.class)
//class UsersControllerTest {
//
//    @Mock
//    private UsersService usersService;
//
//    @InjectMocks
//    private UsersController usersController;
//
//    @Test
//    void loginUser_ValidCredentials_ReturnsOkResponse() {
//        
//        UsersDto usersDto = new UsersDto("test@example.com", "password");
//        Users user = new Users();
//        when(usersService.loginUser(usersDto)).thenReturn(Optional.of(user));
//        ResponseEntity<?> response = usersController.loginUser(usersDto);
//        assertNotNull(response);
//        assertEquals(UsersMapper.toDto(user), response.getBody());
//    }
//
//    
//    @Test
//    void updatePassword_ValidParameters_ReturnsOkResponse() {
//        
//        Long userId = 1L;
//        PasswordUpdateDto passwordUpdateDto = new PasswordUpdateDto("newPassword", "newPassword");
//        doNothing().when(usersService).updatePassword(userId, passwordUpdateDto);
//        ResponseEntity<?> response = usersController.updatePassword(userId, passwordUpdateDto);
//        assertNotNull(response);
//        assertEquals("Password updated successfully", response.getBody());
//    }
//
//}
