package com.ziwei.mall.config;

import com.ziwei.mall.dto.QueueEnum;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 * 用于将队列与交换机绑定
 */

@Configuration
public class RabbitMqConfig {
    /**
     * 订单实际消费(已取消)队列的交换机
     */
    @Bean
    DirectExchange orderExchange() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单延迟队列的交换机
     */
    @Bean
    DirectExchange orderTtlExchange() {
        return ExchangeBuilder
                .directExchange(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange())
                .durable(true)
                .build();
    }

    /**
     * 订单实际消费(被取消)队列
     */
    @Bean
    public Queue orderQueue() {
        return new Queue(QueueEnum.QUEUE_ORDER_CANCEL.getName());
    }

    /**
     * 未处理订单队列
     * "x-dead-order-exchange" 到期后转发给目标交换机
     * "x-dead-order-routing-key" 到期后转发的路由键
     */
    @Bean
    public Queue orderTtlQueue() {
        return QueueBuilder
                .durable(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getName())
                .withArgument("x-dead-order-exchange", QueueEnum.QUEUE_ORDER_CANCEL.getExchange())
                .withArgument("x-dead-order-routing-key", QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey())
                .build();
    }

    /**
     * 将订单队列绑定到交换机
     */
    @Bean
    Binding orderBinding(DirectExchange orderExchange, Queue orderQueue) {
        return BindingBuilder
                .bind(orderQueue)
                .to(orderExchange)
                .with(QueueEnum.QUEUE_ORDER_CANCEL.getRouteKey());
    }

    /**
     *将未处理订单队列绑定到交换机
     */
    @Bean
    Binding orderTtlBinding(DirectExchange orderTtlExchange, Queue orderTtlQueue) {
        return BindingBuilder
                .bind(orderTtlQueue)
                .to(orderTtlExchange)
                .with(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey());
    }

}
