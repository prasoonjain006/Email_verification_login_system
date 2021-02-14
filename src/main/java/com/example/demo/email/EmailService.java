package com.example.demo.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmailService implements EmailSender {

	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}
	private final JavaMailSender mailSender;
	private final static Logger LOGGER =LoggerFactory.getLogger(EmailService.class);
	
	@Override
	@Async
	public void send(String to, String email) {
		// TODO Auto-generated method stub
		try {
			MimeMessage mimeMessage=mailSender.createMimeMessage();
			MimeMessageHelper helper=new MimeMessageHelper(mimeMessage,"utf-8");
			helper.setText(email,true);
			helper.setTo(to);
			helper.setSubject("Confirm your email");
			helper.setFrom("prasoonjain006@gmail.com");
			mailSender.send(mimeMessage);
		}catch( MessagingException e) {
			LOGGER.error("failed to send email",e);
			throw new IllegalStateException("failed to send emaill");
		}
		
	}

	
}
