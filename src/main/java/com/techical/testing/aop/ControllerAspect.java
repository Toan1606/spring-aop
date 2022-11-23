package com.techical.testing.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class ControllerAspect {

    @Pointcut("execution(* com.techical.testing.controller.*.*(..))")
    public void loggingPointcut() {}

    @Before("loggingPointcut()")
    public void beforeLoggingPointcut(JoinPoint joinPoint) {
        log.info("Before method invoked::" + joinPoint.getSignature());
    }

    @After("loggingPointcut()")
    public void afterLoggingPointcut(JoinPoint joinPoint) {
        log.info("After method invoke::" + joinPoint.getSignature());
    }
}
