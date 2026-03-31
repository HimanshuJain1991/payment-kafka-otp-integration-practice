package com.otp.controller;

import com.otp.service.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/otp")
@CrossOrigin
public class OTPController {

    @Autowired
    private OTPService otpService;

    @PostMapping("/send")
    public String sendOtp(@RequestParam String phone) {
        otpService.generateOtp(phone);
        return "OTP Sent!";
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phone, @RequestParam String otp) {
        boolean isValid = otpService.verifyOtp(phone, otp);

        return isValid ? "OTP Verified ✅" : "Invalid or Expired OTP ❌";
    }
}
