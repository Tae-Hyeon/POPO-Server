package com.fortice.popo.global.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CheckPresentAOP {
    @Around("@annotation(com.fortice.popo.global.aop.annotation.CheckPresent)")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable{
        Object returnValue = pjp.proceed();
        return returnValue;
    }
}
