package com.aayush.user_service.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class ExecutionTimeAspect {

    @Pointcut("execution(* com.aayush.user_service.controller.*.*(..))")
    public void controllerMethods() {}

    @Around("controllerMethods()")
    public Object measureExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long start = System.nanoTime();

        try {
            return proceedingJoinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long elapsedNs = end - start;
            long elapsedMs = TimeUnit.NANOSECONDS.toMillis(elapsedNs);

            String signature = proceedingJoinPoint.getSignature().toShortString();

            log.info("Controller Method: {} executed in {} ms",
                    signature, elapsedMs);
        }
    }

}
