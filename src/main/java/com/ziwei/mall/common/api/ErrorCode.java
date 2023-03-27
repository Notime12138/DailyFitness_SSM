package com.ziwei.mall.common.api;

/**
 * @author Ziwei GONG
 * @date 2023/3/27
 * @name mallSpringboot
 * API错误码
 */

public interface ErrorCode {
    long getCode();

    String getMessage();
}
