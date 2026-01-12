//package com.vamshi.mailservice.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MailService {
//	
//	@Autowired
//    private JavaMailSender mailSender;
//
//	public void sendMail(String email, String subject, String text) {
//		SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(email);
//        message.setSubject(subject);
//        message.setText(text);
//
//        mailSender.send(message);
//	}
//
//}

package com.vamshi.mailservice.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MailService {

    @Value("${brevo.api.url}")
    private String url;

    @Value("${brevo.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public void sendMail(String to, String subject, String body) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("api-key", apiKey);

        // Sender
        Map<String, String> sender = new HashMap<>();
        sender.put("email", "mukthavamshi123@gmail");
        sender.put("name", "SecureCard");

        // Receiver
        Map<String, String> receiver = new HashMap<>();
        receiver.put("email", to);

        List<Map<String, String>> toList = new ArrayList<>();
        toList.add(receiver);

        // Request body
        Map<String, Object> payload = new HashMap<>();
        payload.put("sender", sender);
        payload.put("to", toList);
        payload.put("subject", subject);
        payload.put("htmlContent", "<p>" + body + "</p>");

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(payload, headers);

        restTemplate.postForObject(url, request, String.class);
    }
}
