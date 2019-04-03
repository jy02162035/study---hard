package spring.aop;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：增强切面类
 *
 * <p> 创建时间：2019/01/24 15:06:57 </p> 
 * <p> 作者：小D课堂</p>
 */
@Order(1)
@Aspect
@Component
@Slf4j
public class ValidateAop {

	@Before("LoggingAop.pointcutLogging()")
	public void doBefore(JoinPoint joinPoint) {
		// 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
		String methodName = joinPoint.getSignature().getName();
		List<Object> asList = Arrays.asList(joinPoint.getArgs());
		log.info(
				"***************ValidateAop.doBefore ()*******************methodName[" + methodName + "]**********args" + asList);
	}
	
	@Around("LoggingAop.pointcutLogging()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		log.info("**********ValidateAop.around() Before*******************" + joinPoint.getSignature().getName());
		Object rObject = joinPoint.proceed();
		log.info("**********ValidateAop.around() After*******************" + rObject);
		return rObject;
	}
}
