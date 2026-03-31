package com.otp.real.real_otp_system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.concurrent.TimeUnit;

@Service
public class OtpService {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final int OTP_EXPIRY = 2; // minutes
    private static final int MAX_ATTEMPTS = 3;

    public String sendOtp(String phone) {

        // 🚫 Rate limiting (30 sec cooldown)
        String lastSent = redisTemplate.opsForValue().get("OTP_COOLDOWN:" + phone);
        if (lastSent != null) {
            throw new RuntimeException("Wait before requesting again");
        }

        String otp = String.valueOf(100000 + new SecureRandom().nextInt(900000));

        // Store OTP with expiry
        redisTemplate.opsForValue()
                .set("OTP:" + phone, otp, OTP_EXPIRY, TimeUnit.MINUTES);

        // Cooldown key (30 sec)
        redisTemplate.opsForValue()
                .set("OTP_COOLDOWN:" + phone, "1", 30, TimeUnit.SECONDS);

        // Initialize attempts
        redisTemplate.opsForValue()
                .set("OTP_ATTEMPT:" + phone, "0", OTP_EXPIRY, TimeUnit.MINUTES);

        // 📱 Send SMS (simulate)
        System.out.println("OTP: " + otp);

        return "OTP Sent";
    }

    public boolean verifyOtp(String phone, String userOtp) {

        String key = "OTP:" + phone;
        String storedOtp = redisTemplate.opsForValue().get(key);

        if (storedOtp == null) {
            throw new RuntimeException("OTP expired or not found");
        }

        // 🚫 Attempt limit
        String attemptKey = "OTP_ATTEMPT:" + phone;
        int attempts = Integer.parseInt(redisTemplate.opsForValue().get(attemptKey));

        if (attempts >= MAX_ATTEMPTS) {
            throw new RuntimeException("Too many attempts");
        }

        if (storedOtp.equals(userOtp)) {
            redisTemplate.delete(key); // remove after success
            return true;
        } else {
            redisTemplate.opsForValue().increment(attemptKey);
            return false;
        }
    }
}