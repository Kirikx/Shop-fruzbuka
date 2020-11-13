package ru.fruzbuka.controller.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Aspect
@Configuration
public class LogAspects {

    @Before("execution(* ru.fruzbuka.controller.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("Call of {}", joinPoint);
    }

    @Around("@annotation(ru.fruzbuka.controller.aspect.TrackTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed();

        log.info("Time taken by {} is {} ms", joinPoint, System.currentTimeMillis() - start);

        return result;
    }

}
