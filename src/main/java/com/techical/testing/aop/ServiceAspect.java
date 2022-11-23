package com.techical.testing.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ServiceAspect {

    @Pointcut("within(com.techical.testing.services.*)")
    public void serviceLogging(){}

    @Before("serviceLogging()")
    public void before(JoinPoint joinPoint) {
        log.info("Before method invoke:: " +joinPoint.getSignature());
    }

    @After("serviceLogging()")
    public void after(JoinPoint joinPoint) {
        log.info("After method invoke:: " +joinPoint.getSignature());
    }

    @AfterReturning("serviceLogging()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("After returning invoke:: " + joinPoint.getSignature());
    }

    @AfterThrowing("serviceLogging()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.error("After throwing invoke:: " + joinPoint.getSignature());
    }
}
