package com.ziwei.dailyFitness.component;

import com.ziwei.dailyFitness.annotations.CounterRateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ziwei GONG
 * @date 2023/6/7
 * @name DailyFitness
 */

@Aspect
@Component
public class CounterRateLimitAspect {
    private final ConcurrentMap<String, AtomicLong> counterMap;

    public CounterRateLimitAspect() {
        // 初始化计数器映射
        counterMap = new ConcurrentHashMap<>();
    }

    @Around("@annotation(rateLimit)")
    public Object handleRateLimit(ProceedingJoinPoint joinPoint, CounterRateLimit counterRateLimit) throws Throwable {
        String key = counterRateLimit.key();
        int limit = counterRateLimit.limit();

        AtomicLong counter = counterMap.computeIfAbsent(key, k -> new AtomicLong());
        long count = counter.incrementAndGet();

        if (count <= limit) {
            return joinPoint.proceed();
        } else {
            throw new RateLimitExceededException();
        }
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<String> handleRateLimitExceeded(RateLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    public static class RateLimitExceededException extends RuntimeException {
    }
}
