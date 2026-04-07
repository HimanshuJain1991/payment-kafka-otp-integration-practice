package com.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceWithoutAOP {

    // Create Logger
    private static final Logger logger = LoggerFactory.getLogger(OrderServiceWithoutAOP.class);

    public void placeOrder() {
        logger.info("placeOrder() started");      // manual logging
        logger.info("Placing the order...");
        logger.info("placeOrder() ended");        // manual logging
    }

    public void cancelOrder() {
        logger.info("cancelOrder() started");     // manual logging
        logger.info("Cancelling the order...");
        logger.info("cancelOrder() ended");       // manual logging
    }
}