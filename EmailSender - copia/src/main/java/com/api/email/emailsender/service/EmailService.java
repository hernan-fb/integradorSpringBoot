package com.api.email.emailsender.service;

import com.api.email.emailsender.entity.EmailDetails;
import org.springframework.http.ResponseEntity;

public interface EmailService {
    ResponseEntity<String> sendSimpleMail(EmailDetails details);
    ResponseEntity<String> sendMailWithAttachment(EmailDetails details);
}
