/**
 *
 */
package com.imperialm.imiservices.aop;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Dheerajr
 *
 */

@Component
@Aspect
public class IMIServicesMonitor {
	private static Logger logger = LoggerFactory.getLogger(IMIServicesMonitor.class);

	@AfterThrowing(pointcut = "execution(* com.imperialm.imiservices..*.*(..))", throwing = "error")
	public void logAfterThrowing(final JoinPoint joinPoint, final Throwable error) {
		logger.error(joinPoint.getSignature().getName(), error);
	}

	@Around("execution(* com.imperialm.imiservices..*.*(..))")
	public Object processRequestParams(final ProceedingJoinPoint joinPoint) throws Throwable {
		final String packageName = joinPoint.getSignature().getDeclaringTypeName();
		final String methodName = joinPoint.getSignature().getName();

		logger.info(packageName + "." + methodName + " start");
		final Object[] args = joinPoint.getArgs();
		logger.info(Arrays.toString(args));

		final long start = System.currentTimeMillis();
		final Object output = joinPoint.proceed();
		final long elapsedTime = System.currentTimeMillis() - start;
		//logger.info(packageName + "." + methodName + " end : Elapsedtime: " + elapsedTime);
		return output;
	}
}
