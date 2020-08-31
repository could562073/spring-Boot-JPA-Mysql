package com.rex.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;


/**
 * 請求日誌統一處理類 (AOP實現)
 */
@Aspect
@Component
public class WebLogAspect {

    private Logger logger = Logger.getLogger(String.valueOf(getClass()));

    ThreadLocal<Long> executeTime = new ThreadLocal<>();

    /**
     * @Pointcut 針對Service Impl底下所有類別的request
     */
    @Pointcut("execution(public * com.rex.demo.service.Impl..*(..))")
    public void Pointcut() {
    }

    /**
     * @Before 前置通知
     */
    @Before("Pointcut()")
    public void doBefore(JoinPoint joinPoint) {
        executeTime.set(System.currentTimeMillis());
    }

//    /**
//     * @After 後置通知
//     */
//    @After("Pointcut()")
//    public void doAfter(JoinPoint joinPoint) {
//        logger.info("進入後置通知");
//        logger.info(Arrays.toString(joinPoint.getArgs()));
//    }

    /**
     * @AfterRunning: 返回通知 rsult為返回內容
     */
    @AfterReturning(value = "Pointcut()", returning = "result")
    public void afterReturning(JoinPoint joinPoint, Object result) {
        logger.info("request time in " + (System.currentTimeMillis() - executeTime.get()) + " ms");
    }

//    /**
//     * @AfterThrowing 異常通知
//     */
//    @AfterThrowing(value = "Pointcut()", throwing = "e")
//    public void afterThrowing(JoinPoint joinPoint, Exception e) {
//        logger.info(e.toString());
//    }

//    /**
//     * @Around 環繞通知
//     */
//    @Around("Pointcut()")
//    public Object Around(ProceedingJoinPoint pjp) throws Throwable {
//        logger.info("around執行方法之前");
//        Object object = pjp.proceed();
//        logger.info("around執行方法之後--返回值：" + object);
//        return object;
//    }

}
