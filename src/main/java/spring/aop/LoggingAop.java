package spring.aop;

import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：增强切面类
 *
 * <p> 创建时间：2019/01/24 15:06:57 </p> 
 * <p> 作者：小D课堂</p>
 */
@Order(2)
@Component
@Aspect
@Slf4j
public class LoggingAop {
	
	@Pointcut(value="execution(* com.spring.aop.*.*(..))")
	public void pointcutLogging() {}

	@Before("pointcutLogging()")
	public void doBefore(org.aspectj.lang.JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> asList = Arrays.asList(joinPoint.getArgs());
		log.debug(
				"***************LoggingAop.doBefore ()*******************methodName[" + methodName + "]**********args" + asList);
	}

	@After("pointcutLogging()")
	public void doAfter(JoinPoint joinPoint) {
		String methodName = joinPoint.getSignature().getName();
		List<Object> asList = Arrays.asList(joinPoint.getArgs());
		log.debug(
				"***************LoggingAop.doAfter ()*******************methodName[" + methodName + "]**********args" + asList);
	}
	
	@AfterReturning(pointcut="pointcutLogging()", returning="returnObj")
	public void afterReturning(JoinPoint joinPoint, Object returnObj) {
		log.debug(
				"***************LoggingAop.afterReturning ()*******************returnObj[" + returnObj + "]**********");
	}
	
	@AfterThrowing(pointcut="pointcutLogging()", throwing="ex")
	public void afterThrowing(Throwable ex) {
		log.debug("LoggingAop.afterThrowing目标方法中抛出的异常:" + ex);
	}
	
	
	
	
}
