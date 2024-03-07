package com.galaxe.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.galaxe.dto.LoginDto;
import com.galaxe.dto.UserDto;
import com.galaxe.entites.LoginResponse;
import com.galaxe.entites.User;
import com.galaxe.exceptions.UserAlreadyExistsException;
import com.galaxe.exceptions.UserNotFoundException;
import com.galaxe.repositories.UserRepo;

@Service
public class UserService {
	@Autowired
	private UserRepo user1repo;
	@Autowired
    private PasswordEncoder passwordEncoder;
	
	public User saveUser(String email,String username,String password,LocalDate  dateofbirth,byte[] image) {
		try {
			
			
	        // Check if user already exists
	        Optional<User> existingUser = user1repo.findByEmail(email);
	        if (existingUser.isPresent()) {
	            throw new UserAlreadyExistsException("User with email " + email + " already exists");
	        }

	        // If user does not exist, create and save the new user
	        User user = new User();
	        user.setEmail(email);
	        user.setUsername(username);
	        user.setDob(dateofbirth);
	        user.setPassword(passwordEncoder.encode(password));
	        user.setImageUrl(image);
	       	         return user1repo.save(user);
	    } catch (Exception e) {
	        
	        throw new UserAlreadyExistsException("User Already Exists");
	    } 
	}

	
	public String getemailbyusername(String username) {
		User user=user1repo.findByUsername(username);
		 System.out.println(user);
		 if (user != null) {
			
	            return user.getEmail();
	        } else {
	            throw new UserNotFoundException("User not found with username: " + username);
	        }
	}
	
	public void deleteUserById(Long userId) {
        if (user1repo.existsById(userId)) {
            user1repo.deleteById(userId);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    // Method for updating user details
    public User updateUser(Long userId,String email,String username,String password,LocalDate  dateofbirth,byte[] image) {
        return user1repo.findById(userId).map(user -> {
            user.setEmail(email);
            user.setUsername(username);
            user.setPassword(passwordEncoder.encode(password)); 
            user.setDob(dateofbirth);
            user.setImageUrl(image);
            return user1repo.save(user);
        }).orElseThrow(() -> new UserNotFoundException("User not found with ID: " + userId));
    }
	

//Method used to get user by id	
	 public Optional<User> getUserById(Long userId) {
		 
	       Optional<User> user=  user1repo.findById(userId);
	       if(user!=null) {
	    	   return user;
	       }else {
	    	   throw new UserNotFoundException("user not found");
	    	   
	       }
	    }
	 
	 
	 //Method used for logging through user details
//	public LoginResponse  loginEmployee(LoginDto loginDTO) {
//       
//        User user = user1repo.findByUsername(loginDTO.getUsername());
//        if (user != null) {
//            String password = loginDTO.getPassword();
//            String encodedPassword = user.getPassword();
//            Boolean isPwdRight = password.equals(encodedPassword) ;
//            if (isPwdRight) {
//                Optional<User> user1 = user1repo.findOneByUsernameAndPassword(loginDTO.getUsername(), encodedPassword);
//                if (user1.isPresent()) {
//                    return new LoginResponse("Login Success", true,user.getId());
//                } else {
//                    return new LoginResponse("Login Failed", false,null);
//                }
//            } else {
//                return new LoginResponse("password Not Match", false,null);
//            }
//        }else {
//            return new LoginResponse("Username not exists", false,null);
//        }
//    }
	 public LoginResponse loginEmployee(LoginDto loginDTO) {
	        // Find user by username
	        User user = user1repo.findByUsername(loginDTO.getUsername());
	        if (user != null) {
	            // Compare provided password with hashed password
	            if (passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
	                // Passwords match, return successful login response
	                return new LoginResponse("Login Success", true, user.getId());
	            } else {
	                // Passwords don't match, return failed login response
	                return new LoginResponse("Login Failed", false, null);
	            }
	        } else {
	            // User not found, return failed login response
	            return new LoginResponse("Username not exists", false, null);
	        }
	 }
	
	
	
	//Method used to get  list of users
	public List<User> getAllUser1(){
		List<User> users=user1repo.findAll();
		if(users!=null) {
			return users;
		}else {
			throw new UserNotFoundException("user not found");
		}
	}
	
	
	//Method used to get user based on id
	public User getUser1(long id) {
		User user=user1repo.findById(id).orElseThrow(()->new UserNotFoundException("user not found"));
		if(user!=null) {
			return user;
		}else {
			throw new UserNotFoundException("user not found with id :"+id);
		}
	}
	

}
