package com.ziwei.dailyFitness.component;

import com.ziwei.dailyFitness.annotations.TokenBucketRateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author Ziwei GONG
 * @date 2023/6/7
 * @name DailyFitness
 */


@Aspect
@Component
public class TokenBucketRateLimitAspect {
    private final TokenBucketRateLimiter rateLimiter;

    public TokenBucketRateLimitAspect() {
        // 初始化令牌桶限流器
        rateLimiter = new TokenBucketRateLimiter(100, 10); // 令牌桶容量为100，每秒生成10个令牌
    }

    @Around("@annotation(tokenBucketRateLimit)")
    public Object handleRateLimit(ProceedingJoinPoint joinPoint, TokenBucketRateLimit tokenBucketRateLimit) throws Throwable {
        if (rateLimiter.allowRequest()) {
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
