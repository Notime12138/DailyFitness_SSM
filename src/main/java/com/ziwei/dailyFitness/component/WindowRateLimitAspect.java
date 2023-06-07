package com.ziwei.dailyFitness.component;

import com.ziwei.dailyFitness.annotations.WindowRateLimit;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Ziwei GONG
 * @date 2023/6/7
 * @name DailyFitness
 */

@Aspect
@Component
public class WindowRateLimitAspect {

    private static final String LIMIT_KEY_PREFIX = "rate_limit:";
    private static final String LIMIT_REMAINING_SUFFIX = ":remaining";
    private static final String LIMIT_RESET_SUFFIX = ":reset";

    private final RedisTemplate<String, String> redisTemplate;

    public WindowRateLimitAspect(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("@annotation(windowRateLimit)")
    public Object handle(ProceedingJoinPoint joinPoint, WindowRateLimit windowRateLimit) throws Throwable {
        // 获取请求的方法和参数
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        Object[] args = joinPoint.getArgs();

        // 获取限流相关信息
        int limit = windowRateLimit.limit();
        long timeout = windowRateLimit.timeout();
        String key = LIMIT_KEY_PREFIX + method.getName();

        // 获取当前时间戳
        long now = System.currentTimeMillis();

        // 获取限流剩余次数和重置时间戳
        String remainingStr = redisTemplate.opsForValue().get(key + LIMIT_REMAINING_SUFFIX);
        String resetStr = redisTemplate.opsForValue().get(key + LIMIT_RESET_SUFFIX);
        int remaining = remainingStr == null ? limit : Integer.parseInt(remainingStr);
        long reset = resetStr == null ? now + timeout : Long.parseLong(resetStr);

        // 如果当前时间在重置时间之后，则重置限流次数和重置时间
        if (now >= reset) {
            remaining = limit;
            reset = now + timeout;
            redisTemplate.opsForValue().set(key + LIMIT_RESET_SUFFIX, String.valueOf(reset), timeout, TimeUnit.MILLISECONDS);
        }

        // 如果限流次数已用完，则抛出异常
        if (remaining <= 0) {
            throw new RateLimitExceededException();
        }

        // 执行目标方法，并减少限流次数
        redisTemplate.opsForValue().decrement(key + LIMIT_REMAINING_SUFFIX);
        return joinPoint.proceed(args);
    }

    @ExceptionHandler(RateLimitExceededException.class)
    public ResponseEntity<String> handleRateLimitExceeded(RateLimitExceededException ex) {
        return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).build();
    }

    public static class RateLimitExceededException extends RuntimeException {
    }

}
