package com.ziwei.dailyFitness.component;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Ziwei GONG
 * @date 2023/6/7
 * @name DailyFitness
 */

public class TokenBucketRateLimiter {
    private final long capacity; // 令牌桶的容量
    private final long refillTokensPerSecond; // 每秒生成的令牌数量
    private final AtomicLong availableTokens; // 当前可用的令牌数量
    private long lastRefillTimestamp; // 上次填充令牌的时间戳

    public TokenBucketRateLimiter(long capacity, long refillTokensPerSecond) {
        this.capacity = capacity;
        this.refillTokensPerSecond = refillTokensPerSecond;
        this.availableTokens = new AtomicLong(0);
        this.lastRefillTimestamp = System.currentTimeMillis();
    }

    public synchronized boolean allowRequest() {
        refillTokens();
        return tryConsumeToken();
    }

    private void refillTokens() {
        long currentTime = System.currentTimeMillis();
        long elapsedTime = currentTime - lastRefillTimestamp;
        long tokensToRefill = elapsedTime * refillTokensPerSecond / 1000; // 根据时间间隔计算需要填充的令牌数量
        if (tokensToRefill > 0) {
            availableTokens.set(Math.min(availableTokens.get() + tokensToRefill, capacity));
            lastRefillTimestamp = currentTime;
        }
    }

    private boolean tryConsumeToken() {
        long currentTokens = availableTokens.get();
        if (currentTokens == 0) {
            return false; // 令牌桶已空
        }
        return availableTokens.compareAndSet(currentTokens, currentTokens - 1);
    }
}