package com.fortice.popo.global.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogTimeAOP {

    @Around("@annotation(com.fortice.popo.global.aop.annotation.LogTime)")
    public Object logTime(ProceedingJoinPoint pjp) throws Throwable{
        long begin = System.currentTimeMillis();
        Object returnValue = pjp.proceed(); // 메서드 호출 자체를 감쌈
        System.out.println(System.currentTimeMillis() - begin);
        return returnValue;
    }
}
