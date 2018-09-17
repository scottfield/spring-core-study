package com.jackie.study.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectExample {
    @Before("execution(* com.jackie.study.spring.aop..*(..))")
    public void beforeDoSomeCheck() {
        System.out.println("do some check before method invocation");
    }
}
