package com.twilio.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpService {

    private Map<String, String> otpStorage = new HashMap<>();

    @Autowired
    private SmsService smsService;

    // Generate OTP
    public String generateOtp(String phoneNumber) {

        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStorage.put(phoneNumber, otp);

        // Send SMS
        smsService.sendOtp(phoneNumber, otp);

        return "OTP sent successfully";
    }

    // Verify OTP
    public String verifyOtp(String phoneNumber, String otp) {

        String storedOtp = otpStorage.get(phoneNumber);

        if (storedOtp != null && storedOtp.equals(otp)) {
            otpStorage.remove(phoneNumber);
            return "OTP verified successfully";
        } else {
            return "Invalid OTP";
        }
    }
}
