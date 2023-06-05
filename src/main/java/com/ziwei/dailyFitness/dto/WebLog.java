package com.ziwei.dailyFitness.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ziwei GONG
 * @date 2023/4/10
 * @name DailyFitnessSpringboot
 * 封装Logger中的日志信息
 */

@Getter
@Setter
public class WebLog {
    /**
     * 描述
     */
    private String description;

    /**
     * 用户
     */
    private String username;

    /**
     * 调用的时间
     */
    private Long invokeTime;

    /**
     * 花费时间
     */
    private Integer spendTime;

    /**
     * 根路径
     */
    private String basePath;

    /**
     * 统一资源标识符
     */
    private String uri;

    /**
     * 统一资源定位符
     */
    private String url;

    /**
     * 请求类型
     */
    private String method;

    /**
     * ip地址
     */
    private String ip;

    /**
     * 请求参数
     */
    private Object parameter;

    /**
     * 请求结果
     */
    private Object result;
}
