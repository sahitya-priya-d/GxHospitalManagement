package com.galaxe.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;

import com.galaxe.entites.User;
import com.galaxe.repositories.UserRepo;

@RestController
public class ScheduledController {
	
	
@Autowired
private UserRepo userrepo;
	
	
	  @Scheduled(cron = "0 52 15 * * ?") // Run at 12:00 PM every day  month date 
	    public void sendBirthdayWishes() {
	        LocalDate today = LocalDate.now();
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");

	        List<User> users = userrepo.findAll();
	        for (User user : users) {
	            String name = user.getUsername();
	            LocalDate bdayDate = user.getDob();

	            if (bdayDate.getMonth() == today.getMonth() ) {
	                System.out.println("Happy Birthday, " + name + "!");
	            }
	        }

}
}
