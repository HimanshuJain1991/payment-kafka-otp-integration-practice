package com.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.aop.OrderService.*(..))")
    public void logBefore(JoinPoint jp) {
        System.out.println(
                "LOG: " + jp.getSignature().getName() + "() started"
        );
    }

    @After("execution(* com.aop.OrderService.*(..))")
    public void logAfter(JoinPoint jp) {
        System.out.println(
                "LOG: " + jp.getSignature().getName() + "() ended"
        );
    }

}