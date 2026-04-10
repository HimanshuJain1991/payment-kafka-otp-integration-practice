package com.oauth2.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Welcome! Click /user to see details";
    }

    @GetMapping("/user")
    public Object user(@AuthenticationPrincipal OAuth2User user) {
        return user.getAttributes(); // 👈 user data from Google
    }
}
