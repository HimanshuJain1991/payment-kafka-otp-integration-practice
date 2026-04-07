package com.aop;

import org.springframework.stereotype.Service;

@Service
public class OrderService {

    public void placeOrder() {
        //System.out.println("LOG: placeOrder() started");

        System.out.println("Placing the order...");

       // System.out.println("LOG: placeOrder() ended");
    }

    public void cancelOrder() {
       // System.out.println("LOG: cancelOrder() started");

        System.out.println("Cancelling the order...");

        //System.out.println("LOG: cancelOrder() ended");
    }

}