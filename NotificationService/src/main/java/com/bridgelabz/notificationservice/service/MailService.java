package com.bridgelabz.notificationservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.notificationservice.model.Mail;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class MailService {
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${mailPassword}")
	private String password;

	public void sendMail(Mail mail) {
		log.info("Mail service method ===> mailId "+mail.getTo());
		log.info("Mail service method ===> mailPassword ===> "+password);
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(mail.getTo());
		message.setSubject(mail.getSubject());
		message.setText(mail.getContext());
		mailSender.send(message);
	}

}
