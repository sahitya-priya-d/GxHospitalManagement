package com.galaxe.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.galaxe.dto.LoginDto;
import com.galaxe.dto.UserDto;
import com.galaxe.entites.LoginResponse;
import com.galaxe.entites.User;
import com.galaxe.exceptions.UserNotFoundException;
import com.galaxe.repositories.UserRepo;
import com.galaxe.service.EmailService1;
import com.galaxe.service.UserService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/user")

public class UserController {

	@Autowired
	private UserService userservice;
	
	   @Autowired
	    private EmailService1 emailService;
	   
	   @Autowired
	   private UserRepo userepo;
	   
	  
	   
	   
	   @PostMapping("/signup")
	
	   public ResponseEntity<User> saveUser(@RequestParam String email,@RequestParam String password,@RequestParam String username,@RequestParam LocalDate date,@RequestParam MultipartFile image) {
	       try {
	    	   byte[] imageurl=image.getBytes();
	    	   userservice.saveUser(email,username,password,date,imageurl);
	    	   return new ResponseEntity<User>(HttpStatus.CREATED);
	       }catch(Exception e) {
	    	   return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
	       }
	       
	   }


	//Get all users
	
	@GetMapping("/alluser")
	
	public ResponseEntity<List<User>> getallUsers(){
	 // Retrieve and return all users
	 return ResponseEntity.ok(userservice.getAllUser1());
	}

	//Get user by ID
	
	@GetMapping("/all/{id}")
	
	
	public ResponseEntity<User> getUser(@PathVariable long id) throws UserNotFoundException{
	 // Retrieve and return user by ID
	 return ResponseEntity.ok(userservice.getUser1(id));
	}

	//Login user
	
	@PostMapping("/login")
	
	public ResponseEntity<LoginResponse> loginEmployee(@RequestBody LoginDto loginDTO)
	{
	 // Call userservice to authenticate and login the user
	 LoginResponse loginResponse = userservice.loginEmployee(loginDTO);
	 
	
	 return ResponseEntity.ok(loginResponse);
	}
	
	 // Endpoint for deleting user by ID

	  
    @DeleteMapping("/delete/{userId}")
   
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userservice.deleteUserById(userId);
        return ResponseEntity.ok("User with ID " + userId + " deleted successfully.");
    }

    // Endpoint for updating user details

	 
    @PutMapping("/update/{userId}")
  
    public ResponseEntity<User> updateUser(@PathVariable Long userId, @RequestParam String email,@RequestParam String username,@RequestParam String password,@RequestParam LocalDate date,@RequestParam MultipartFile image) throws IOException {
    	byte[] imageurl=image.getBytes();
        User updatedUser = userservice.updateUser(userId, email,username,password,date,imageurl);
        return ResponseEntity.ok(updatedUser);
    }
	

	   
    @GetMapping("/{username}")
    
    public ResponseEntity<?> getUserEmail(@PathVariable String username) {
        String email = userservice.getemailbyusername(username);
        System.out.println(email);
       return ResponseEntity.ok(email);
     
    }

    @PostMapping("/send-email/{email}")

    public ResponseEntity<String> sendEmail(@PathVariable String email) {
        try {
            emailService.sendLoginEmail(email);
            return ResponseEntity.ok("Email sent successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to send email: " + e.getMessage());
        }
    }
	
	
}
