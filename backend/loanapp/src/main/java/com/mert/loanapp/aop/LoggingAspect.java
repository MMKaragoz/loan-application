package com.mert.loanapp.aop;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

	// set up logger
	private Logger logger = Logger.getLogger(getClass().getName());
	
	// set up pointcut declarations
	
	// controller
	@Pointcut("execution (* com.mert.loanapp.controller..*.*(..))")
	private void forControllerPackage() {
		
	}
	
	// service
	@Pointcut("execution (* com.mert.loanapp.service..*.*(..))")
	private void forServicePackage() {
		
	}
	
	// converter
	@Pointcut("execution (* com.mert.loanapp.converter..*.*(..))")
	private void forConverterPackage() {
		
	}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forConverterPackage()")
	private void forAppFlow() {
		
	}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint joinPoint) {
		
		// display method we are calling
		String method = joinPoint.getSignature().toShortString();
		logger.info("calling method: " + method);
		
		// display the arguments to the method
		Object[] args = joinPoint.getArgs();
		for(Object arg: args) {
			logger.info("argument: " + arg);
		}
	}
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning= "result")
	public void afterReturning(JoinPoint joinPoint, Object result) {
		
		// display method we are returning
		String method = joinPoint.getSignature().toShortString();
		logger.info("returning from method: " + method);
		
		// display data returned
		logger.info("result: " + result );
	}
}
