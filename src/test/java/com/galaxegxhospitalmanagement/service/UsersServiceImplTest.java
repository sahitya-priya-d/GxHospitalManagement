//package com.galaxegxhospitalmanagement.service;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//import java.util.Optional;
//
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.galaxe.gxhospitalmanagement.dto.PasswordUpdateDto;
//import com.galaxe.gxhospitalmanagement.dto.UsersDto;
//import com.galaxe.gxhospitalmanagement.entity.Users;
//import com.galaxe.gxhospitalmanagement.exception.PasswordMismatchingException;
//import com.galaxe.gxhospitalmanagement.exception.UserAuthenticationException;
//import com.galaxe.gxhospitalmanagement.exception.UserNotFoundException;
//import com.galaxe.gxhospitalmanagement.repository.UsersRepository;
//import com.galaxe.gxhospitalmanagement.service.EmailServiceImpl;
//import com.galaxe.gxhospitalmanagement.service.UsersServiceImpl;
//
//@ExtendWith(MockitoExtension.class)
//public class UsersServiceImplTest {
//
//	@Mock(lenient = true)
//	private UsersRepository userRepository;
//
//	@Mock
//	private EmailServiceImpl emailService;
//
//	@InjectMocks
//	private UsersServiceImpl usersService;
//
//	@Test
//    void loginUser_ValidUser_ReturnsUser() {
//        when(userRepository.findByEmailAndPassword("test@example.com", "password123"))
//                .thenReturn(Optional.of(new Users()));
//        Optional<Users> result = usersService.loginUser(new UsersDto("test@example.com", "password123"));
//        assertTrue(result.isPresent());
//    }
//
//	@Test
//    void loginUser_InvalidUser_ThrowsException() {
//
//        when(userRepository.findByEmailAndPassword("invalid@example.com", "wrongPassword"))
//                .thenReturn(Optional.empty());
//        assertThrows(UserAuthenticationException.class, () -> {
//            usersService.loginUser(new UsersDto("invalid@example.com", "wrongPassword"));
//        });
//    }
//
//	@Test
//    void findUserByEmail_UserFound_ReturnsSuccessMessage() {
//        
//        when(userRepository.findByEmail("test@example.com")).thenReturn(Optional.of(new Users()));
//        String result = usersService.findUserByEmail("test@example.com");
//        assertEquals("Reset password email sent successfully!", result);
//    }
//
//	@Test
//    void findUserByEmail_UserNotFound_ThrowsException() {
//        
//        when(userRepository.findByEmail("nonexistent@example.com")).thenReturn(Optional.empty());
//        assertThrows(UserNotFoundException.class, () -> {
//            usersService.findUserByEmail("nonexistent@example.com");
//        });
//    }
//
//	@Test
//    void updatePassword_PasswordMatch_UpdatesPassword() {
//      
//        when(userRepository.findById(1L)).thenReturn(Optional.of(new Users()));
//        assertDoesNotThrow(() -> {
//            usersService.updatePassword(1L, new PasswordUpdateDto("newPassword", "newPassword"));
//        });
//
//    }
//
//	@Test
//    void updatePassword_PasswordMismatch_ThrowsException() {
//       
//        when(userRepository.findById(2L)).thenReturn(Optional.of(new Users()));
//        assertThrows(PasswordMismatchingException.class, () -> {
//            usersService.updatePassword(2L, new PasswordUpdateDto("newPassword", "wrongConfirmPassword"));
//        });
//
//       
//    }
//
//	@Test
//    void updatePassword_UserNotFound_ThrowsException() {
//        
//        when(userRepository.findById(3L)).thenReturn(Optional.empty());
//        assertThrows(UserNotFoundException.class, () -> {
//            usersService.updatePassword(3L, new PasswordUpdateDto("newPassword", "newPassword"));
//        });
//    }
//
//}