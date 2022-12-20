package com.example.ajc_backend.web;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.SendEmail;
import com.example.ajc_backend.services.interfaces.EmailService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RestController
@CrossOrigin("*")
public class MailController {

    @Autowired
    EmailService emailService;
    

    @PostMapping(path = "/sendEmail")
    public MessageResponse sendEmail(@RequestBody SendEmail sendEmail)
                    throws JsonMappingException, JsonProcessingException, IOException {
            return emailService.sendEmail(sendEmail);
    }
}
