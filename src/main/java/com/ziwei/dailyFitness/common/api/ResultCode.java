package com.ziwei.dailyFitness.common.api;

/**
 * @author Ziwei GONG
 * @date 2023/3/27
 * @name DailyFitnessSpringboot
 * 常用HTTP返回码
 */

public enum ResultCode implements ErrorCode {
    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "参数检测失败"),
    UNAUTHORIZED(401, "未获得授权，请检查登录状态或token"),
    FORBIDDEN(403, "没有相关权限");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
