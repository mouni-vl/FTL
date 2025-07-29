package com.example.fantasy.shared.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.util.Arrays;

/**
 * Aspect for logging method execution time and parameters
 */
@Slf4j
@Aspect
@Component
public class LoggingAspect {

    /**
     * Pointcut for all service methods
     */
    @Pointcut("execution(* com.example.fantasy.application.service.*.*(..))")
    public void servicePointcut() {
        // Pointcut definition
    }

    /**
     * Log method execution time and parameters
     * @param joinPoint the join point
     * @return the result of the method execution
     * @throws Throwable if an error occurs
     */
    @Around("servicePointcut()")
    public Object logAroundServiceMethods(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        
        // Log method entry with parameters
        log.debug("Entering: {}.{}() with arguments: {}", 
                className, methodName, Arrays.toString(joinPoint.getArgs()));
        
        // Measure execution time
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        
        Object result;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error("Exception in {}.{}() with cause = {}", 
                    className, methodName, e.getMessage(), e);
            throw e;
        } finally {
            stopWatch.stop();
            log.debug("Exiting: {}.{}() with execution time: {} ms", 
                    className, methodName, stopWatch.getTotalTimeMillis());
        }
        
        return result;
    }
}
