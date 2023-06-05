package com.ziwei.dailyFitness.dto;

import lombok.Getter;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name DailyFitnessSpringboot
 */

@Getter
public enum QueueEnum {
    /**
     * 消息通知队列
     */
    QUEUE_ORDER_CANCEL("mall.order.direct", "mall.order.cancel","mall.order.cancel"),
    /**
     * 消息通知存活时间队列an
     */
    QUEUE_TTL_ORDER_CANCEL("mall.order.direct.ttl","mall.order.cancel.ttl","mall.order.cancel.ttl");

    private String exchange;

    private String name;

    private String routeKey;

    QueueEnum(String exchange, String name, String routeKey) {
        this.exchange = exchange;
        this.name = name;
        this.routeKey = routeKey;
    }
}
