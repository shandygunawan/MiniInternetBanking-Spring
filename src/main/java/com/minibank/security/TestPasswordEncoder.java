package com.minibank.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class TestPasswordEncoder {

    public static void main(String[] args) {
        String password = "Test1234";
        PasswordEncoder pwEncoder = new BCryptPasswordEncoder();
        String encodedPassword = pwEncoder.encode(password);
        System.out.print(encodedPassword);
    }

}
