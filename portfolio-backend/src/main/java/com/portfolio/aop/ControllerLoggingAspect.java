package com.portfolio.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(ControllerLoggingAspect.class);

    //   Pointcut for all controllers
    @Pointcut("execution(* com.portfolio.controller..*(..))")
    public void controllerMethods() {
    	
    }

    //  Around advice → covers before + after + exception
    @Around("controllerMethods()")
    public Object logControllerExecution(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long startTime = System.currentTimeMillis();

        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();

        log.info("➡️ API START | {}.{}()", className, methodName);

        // Log arguments
        Object[] args = joinPoint.getArgs();
        if (args != null && args.length > 0) {
            for (Object arg : args) {
                log.info("   🔹 Request Arg: {}", arg);
            }
        }

        try {
            Object result = joinPoint.proceed();

            long timeTaken = System.currentTimeMillis() - startTime;
            log.info("✅ API SUCCESS | {}.{}() | Time: {} ms",
                    className, methodName, timeTaken);

            return result;

        } catch (Exception ex) {
            long timeTaken = System.currentTimeMillis() - startTime;

            log.error("❌ API ERROR | {}.{}() | Time: {} ms | Message: {}",
                    className, methodName, timeTaken, ex.getMessage());

            throw ex; // VERY IMPORTANT
        }
    }
}
