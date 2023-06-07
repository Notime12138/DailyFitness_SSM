package com.ziwei.dailyFitness.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Ziwei GONG
 * @date 2023/6/7
 * @name DailyFitness
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CounterRateLimit {
    String key(); // 限流的唯一标识
    int limit(); // 限流阈值
}
