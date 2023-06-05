package com.ziwei.dailyFitness.common.api;

/**
 * @author Ziwei GONG
 * @date 2023/3/27
 * @name DailyFitnessSpringboot
 * API错误码
 */

public interface ErrorCode {
    long getCode();

    String getMessage();
}
