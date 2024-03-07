package com.galaxe.gxhospitalmanagement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
@Service
public class EmailServiceImpl {

	@Autowired
	private JavaMailSender javaMailSender;

	  public void sendResetpasswordEmail(String toEmail,Long id) {
	    	 try {
	             MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	             MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

	             helper.setTo(toEmail);
	             helper.setSubject("Customer account password reset ");

	             
	             String link = String.format("http://localhost:3000/forgot-password/%d", id);
	             String content = "<html><body><p style='text-align: center; color:  #8c5a4f'>Follow this link to reset your customer account . If you didn't request a new password, you can safely delete this email. " +
	                     "Click <a href='" + link + "'>Reset your Password</a> </body></html>";
	             helper.setText(content, true);
	             javaMailSender.send(mimeMessage);
	         } catch (MessagingException e) {
	             
	             e.printStackTrace();
	         }
	    }
}
