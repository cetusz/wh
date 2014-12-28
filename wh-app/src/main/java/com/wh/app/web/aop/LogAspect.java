/**
 * 接口方法aop拦截监控
 * 
 * @author Benjamin
 * 
 * 
 */
package com.wh.app.web.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LogAspect {
  
	private static final Logger logger = Logger.getLogger(LogAspect.class);
	@Before("pointcut()")  
	public void beforLog(JoinPoint point){
		System.out.println(point.getTarget().getClass().toString());
	}
	@Pointcut("@annotation(com.wh.app.web.aop.MonitorFun)")
	public void pointcut(){
		System.out.println("Monitor Function executing");
	}
	@After("pointcut()")
	public void afterLog(){
	}
	
	//方法执行的前后调用 
	@Around("pointcut()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		    long begin = System.nanoTime();  
	        Object o = pjp.proceed();    
	        long end = System.nanoTime();  
	        logger.info(pjp.getSignature().toShortString()+" processes "+(end-begin)/1000000+" ms");  
	        return o;  
	}
	

}
