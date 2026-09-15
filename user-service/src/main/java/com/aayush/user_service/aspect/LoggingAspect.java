package com.aayush.user_service.aspect;


// class which contains cross cutting logic
/*
wrapping target beans inside dynamically generated runtime proxies. When another component injects your bean, Spring provides a reference to this surrogate proxy object rather than the raw instance in the heap.
 */

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j

public class LoggingAspect {

    /*
    security concerns :
    dont want to print sensitive information to console
     */


    // where to apply the aspect
    @Pointcut("execution(* com.aayush.user_service.service.*.*(..))")
    public void serviceMethods() {}

    @Before("serviceMethods()")
    public void logBefore(JoinPoint joinPoint) {
        log.info("Called Service Method: {} with arguments: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Service Mehtod: {}, returned: {}",
                joinPoint.getSignature().getName(), joinPoint.getArgs());
    }
}
