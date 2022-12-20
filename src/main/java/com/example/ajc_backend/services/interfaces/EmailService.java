package com.example.ajc_backend.services.interfaces;

import com.example.ajc_backend.MessageResponse;
import com.example.ajc_backend.SendEmail;

public interface EmailService {
    MessageResponse sendEmail(SendEmail sendEmail);

}
