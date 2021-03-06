package com.bridgelabz.notificationservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.notificationservice.model.Mail;
import com.bridgelabz.notificationservice.model.Response;
import com.bridgelabz.notificationservice.service.MailService;

@RestController
@RequestMapping("/notification")
public class NotificationController {
	
	@Autowired
	private MailService mailService;

	@PostMapping("/sendMail")
	private ResponseEntity<Response> sendMail(@RequestBody Mail mail){
	
		mailService.sendMail(mail);
		return ResponseEntity.ok().body(new Response(HttpStatus.OK.value(), "Mail Sent successfully...."));
	}
}
