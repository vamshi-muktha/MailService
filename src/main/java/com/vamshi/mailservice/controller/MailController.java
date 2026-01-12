package com.vamshi.mailservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.vamshi.mailservice.Dto.MailRequest;
import com.vamshi.mailservice.service.MailService;

@RestController
public class MailController {
	@Autowired
	MailService ms;
	@PostMapping("/mail/send")
	public String sendMail(@RequestBody MailRequest mr) {
		ms.sendMail(mr.getTo(), mr.getSubject(), mr.getBody());
		return "mail sent successfully";
	}
}
