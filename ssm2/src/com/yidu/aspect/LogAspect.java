package com.yidu.aspect;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
/**
 * 日志切面类
 * @author Administrator
 *
 */
@Component
@Aspect
public class LogAspect {
	//前置通知：调业务逻辑之前
   public  void before(JoinPoint joinPoint){
	   //类路径：调用的哪个类
	   String className=joinPoint.getTarget().getClass().getName();
	   //调用了那个方法：方法名称
	   String methodName=joinPoint.getSignature().getName();
	   Object[] obj=joinPoint.getArgs();
	   List<Object> list=Arrays.asList(obj);
	   Logger logger=Logger.getLogger( LogAspect.class);
	  logger.warn("前调用了"+className+"类"+"方法是："+methodName+"参数是:"+list);
   } 
   

   public  void after(JoinPoint joinPoint){
	   //类路径：调用的哪个类
	   String className=joinPoint.getTarget().getClass().getName();
	   //调用了那个方法：方法名称
	   String methodName=joinPoint.getSignature().getName();
	   Object[] obj=joinPoint.getArgs();
	   List<Object> list=Arrays.asList(obj);
	   Logger logger=Logger.getLogger( LogAspect.class);
	   logger.warn("前调用了"+className+"类"+"方法是："+methodName+"参数是:"+list);
   } 

	public void afterReturning(JoinPoint joinPoint,Object result){
		String className=joinPoint.getTarget().getClass().getName();
		//调用了那个方法：方法名称
		String methodName = joinPoint.getSignature().getName();
		Object[] obj = joinPoint.getArgs();
		List<Object> list = Arrays.asList(obj);
		System.out.println("调用了"+className+"类"+"方法是："+methodName+"参数是："+list);
		System.out.println("返回值是"+result);
		Logger logger=Logger.getLogger(className);
		logger.warn("调用了"+className+"类"+"方法是："+methodName+"参数是："+list+"返回值是："+result);
	}
	/**
	 * 环绕通知
	 * @param p
	 */
	public Object around(ProceedingJoinPoint p) {
		System.out.println("开始环绕");
		Object obj=null;
		try {
			obj = p.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println("结束环绕");
		return obj;
	}
}
