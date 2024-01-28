package com.example.dhanilonka.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

//import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

//import com.example.dhanilonka.service.DhaniService;

@Aspect
@Component
public class helper {

	Logger log=LoggerFactory.getLogger(helper.class);
	
	@Pointcut(value="execution(* com.example.dhanilonka.*.*.*(..))")
	public void mypointcut()
	{
		
	}
	@Around(value = "mypointcut()")
	public Object mylogger(ProceedingJoinPoint jp) throws Throwable
	{
		ObjectMapper mapper=new ObjectMapper();
		String methodname=jp.getSignature().getName();
		String classname=jp.getTarget().getClass().toString();
		Object[] array=jp.getArgs();
		log.info("method invoked : "+classname+" : "+methodname+" arguments : "+mapper.writeValueAsString(array));
		Object p=jp.proceed();
		log.info(classname+" : "+methodname+" Response : "+mapper.writeValueAsString(p));
		return p;
	}
}
