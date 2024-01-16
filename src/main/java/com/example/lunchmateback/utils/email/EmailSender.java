package com.example.lunchmateback.utils.email;

import org.springframework.http.ResponseEntity;

public interface EmailSender {
    void send(String to, String email);
}
