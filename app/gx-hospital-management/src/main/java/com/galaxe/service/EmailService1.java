package com.galaxe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
	public class EmailService1 {

	    @Autowired
	    private JavaMailSender emailSender;

	    public void sendLoginEmail(String recipientEmail)  {
	        MimeMessage message = emailSender.createMimeMessage();
	        try {
	        MimeMessageHelper helper = new MimeMessageHelper(message, true);

	        helper.setTo(recipientEmail);
	        helper.setSubject("Login Successful");
	        helper.setText("Hello,"+"\r\n"
	        		+ "We are writing to inform you that you have successfully logged into your account.\r\n"
	        		
	        		+ "As a reminder, never share your login credentials with anyone.\r\n"
	        		+ "If you have any questions or concerns, please don't hesitate to contact us.\r\n"
	        		+ "Thank you for using our service.");

	        emailSender.send(message);
	    } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }
	}
}


