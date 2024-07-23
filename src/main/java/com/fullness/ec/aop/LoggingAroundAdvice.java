package com.fullness.ec.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAroundAdvice {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
  
    @Around("execution(* com.example.demo.di.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
      logger.info("▼▼▼▼▼▼▼▼▼▼"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"()"+"▼▼▼▼▼▼▼▼▼▼");
      Arrays.stream(joinPoint.getArgs()).forEach( arg -> logger.info("引数の値:" + arg) );
      Object result = joinPoint.proceed();
      logger.info("戻り値:"+result);
      logger.info("▲▲▲▲▲▲▲▲▲▲"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName()+"()"+"▲▲▲▲▲▲▲▲▲▲");
      return result;
    }
  }
