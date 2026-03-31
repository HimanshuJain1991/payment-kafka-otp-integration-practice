package com.twilio.controller;



import com.twilio.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private OtpService otpService;

    // Send OTP
    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        return otpService.generateOtp(phoneNumber);
    }

    // Verify OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber,
                            @RequestParam String otp) {
        return otpService.verifyOtp(phoneNumber, otp);
    }
}