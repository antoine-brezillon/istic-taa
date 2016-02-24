package fr.istic.taa.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by Antoine Brezillon on 21/01/16.
 */

@Component
@Aspect
public class LoggingAspect {

    private String getClass(String s){
        return s.substring(s.lastIndexOf(".")+1,s.indexOf("@"));
    }

    @Before("execution(* fr..*Resource.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Query\t" + getClass(joinPoint.getThis().toString()) + "\t : \t" + joinPoint.getSignature().getName());
    }

    @After("execution(* fr..*Resource.*(..))")
    public void logAfter(JoinPoint joinPoint) {
        System.out.println("Response\t" + getClass(joinPoint.getThis().toString()) + "\t : \t" + joinPoint.getSignature().getName());
    }

}
