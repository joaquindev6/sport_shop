package com.jfarro.app.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderByCrypt {

    private static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static String encoderByCryp(String password) {
        return passwordEncoder.encode(password);
    }
}
