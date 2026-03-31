package com.otp.real.real_otp_system.controller;

import com.otp.real.real_otp_system.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/otp")
@CrossOrigin
public class OtpController {

    @Autowired
    private OtpService otpService;

    // ✅ SEND OTP API
    @PostMapping("/send")
    public ResponseEntity<?> sendOtp(@RequestBody Map<String, String> request) {
        try {
            String phone = request.get("phone");

            if (phone == null || phone.isEmpty()) {
                return ResponseEntity.badRequest().body("Phone number is required");
            }

            String response = otpService.sendOtp(phone);

            return ResponseEntity.ok(Map.of(
                    "status", "success",
                    "message", response
            ));

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(Map.of(
                    "status", "error",
                    "message", ex.getMessage()
            ));
        }
    }

    // ✅ VERIFY OTP API
    @PostMapping("/verify")
    public ResponseEntity<?> verifyOtp(@RequestBody Map<String, String> request) {
        try {
            String phone = request.get("phone");
            String otp = request.get("otp");

            if (phone == null || otp == null) {
                return ResponseEntity.badRequest().body("Phone and OTP are required");
            }

            boolean isValid = otpService.verifyOtp(phone, otp);

            if (isValid) {
                return ResponseEntity.ok(Map.of(
                        "status", "success",
                        "message", "OTP Verified ✅"
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of(
                        "status", "error",
                        "message", "Invalid OTP ❌"
                ));
            }

        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                    "status", "error",
                    "message", ex.getMessage()
            ));
        }
    }
}