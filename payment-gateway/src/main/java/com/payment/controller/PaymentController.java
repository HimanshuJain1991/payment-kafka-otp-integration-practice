package com.payment.controller;

import com.payment.dto.OrderResponse;
import com.payment.service.PaymentService;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/payment")
@CrossOrigin(origins = "*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Create order API
    @PostMapping("/create-order")
    public OrderResponse createOrder(@RequestParam int amount) throws Exception {

        Order order = paymentService.createOrder(amount);
        OrderResponse res = new OrderResponse();
        res.setId(order.get("id").toString());
        res.setAmount((Integer) order.get("amount"));
        res.setCurrency(order.get("currency").toString());

        return res;
    }

    // Verify payment API
    @PostMapping("/verify-payment")
    public String verifyPayment(@RequestBody Map<String, String> data) throws Exception {
        String orderId = data.get("razorpay_order_id");
        String paymentId = data.get("razorpay_payment_id");
        String signature = data.get("razorpay_signature");

        boolean isValid = paymentService.verifyPayment(orderId, paymentId, signature);

        if (isValid) {
            return "Payment Successful & Verified";
        } else {
            return "Payment verification failed!";
        }
    }
}