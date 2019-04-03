package spring.aop.sample;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

@Aspect
public class ConcurrentOperationExecutor implements Ordered {

	private static final int DEFAULT_MAX_RETRIES = 2;

	private int maxRetries = DEFAULT_MAX_RETRIES;
	private int order = 1;

	public void setMaxRetries(int maxRetries) {
		this.maxRetries = maxRetries;
	}

	public int getOrder() {
		return this.order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	@Around("com.spring.aop.sample.SystemArchitecture.businessService()")
	public Object doConcurrentOperation(ProceedingJoinPoint pjp) throws Throwable {
		// 环绕通知处理方法
		int numAttempts = 0;
		Exception lockFailureException;
		do {
			numAttempts++;
			try {
				System.out.println("环绕通知方法［ doConcurrentOperation(ProceedingJoinPoint pjp) ］.............");
				return pjp.proceed();
			} catch (Exception ex) {
				lockFailureException = ex;
			}
		} while (numAttempts <= this.maxRetries);
		throw lockFailureException;
	}

}