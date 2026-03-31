package com.otp.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OTPService {
    private Map<String, String> otpStore = new HashMap<>();
    private Map<String, Long> expiryStore = new HashMap<>();

    public String generateOtp(String phone) {
        String otp = String.valueOf(100000 + new Random().nextInt(900000));

        otpStore.put(phone, otp);
        expiryStore.put(phone, System.currentTimeMillis() + 2 * 60 * 1000); // 2 min

        System.out.println("OTP for " + phone + " is: " + otp); // simulate SMS

        return otp;
    }

    public boolean verifyOtp(String phone, String otp) {
        if (!otpStore.containsKey(phone)) return false;

        String storedOtp = otpStore.get(phone);
        long expiry = expiryStore.get(phone);

        if (System.currentTimeMillis() > expiry) {
            otpStore.remove(phone);
            return false;
        }

        return storedOtp.equals(otp);
    }
}
