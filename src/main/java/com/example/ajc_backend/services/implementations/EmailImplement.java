package com.example.ajc_backend.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.SendEmail;
import com.example.ajc_backend.services.interfaces.EmailService;

@Service
public class EmailImplement implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}") private String sender;

    @Override
    public MessageResponse sendEmail(SendEmail sendEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo(sendEmail.getTo());
        message.setText(sendEmail.getMessage());
        message.setSubject(sendEmail.getSubject());
        javaMailSender.send(message);
        System.out.println("Mail Send...");
       
        return new MessageResponse("Mail Send...", true);
    }

}
