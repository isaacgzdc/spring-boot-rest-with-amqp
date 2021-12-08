package com.example.exibition.aspect;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class MonitoringAspect {

	@Around("@annotation(Monitoring)")
	public Object executionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		
		Instant now = Instant.now();
		Object methodResult = joinPoint.proceed();
		Instant after = Instant.now();

		long nanos = ChronoUnit.NANOS.between(now, after);
		long sec = ChronoUnit.SECONDS.between(now, after);
		long micro = ChronoUnit.MICROS.between(now, after);
		long millis = ChronoUnit.MILLIS.between(now, after);
		
		log.info("[**] Duration of method ({}) execution: {} sec {} millis {} micro {} nanos", joinPoint.getSignature().getName()
				,sec,millis,micro,nanos);

		return methodResult;

	}

}
