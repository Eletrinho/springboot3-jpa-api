package com.test.coursespring.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncoderService {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String getPasswordHash(String password) {
        return encoder.encode(password);
    }

    public static boolean verifyPassword(String password, String hashedPassword) {
        return encoder.matches(password, hashedPassword);
    }

}