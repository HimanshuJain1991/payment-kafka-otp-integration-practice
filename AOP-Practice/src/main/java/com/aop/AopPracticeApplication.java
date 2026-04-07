package com.aop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AopPracticeApplication {

	public static void main(String[] args) {

        org.springframework.context.ApplicationContext ctx =SpringApplication.run(AopPracticeApplication.class, args);
                com.aop.OrderServiceWithoutAOP orderService = ctx.getBean(OrderServiceWithoutAOP.class);
          orderService.placeOrder();orderService.cancelOrder();
    }

}
