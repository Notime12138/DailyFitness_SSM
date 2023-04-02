package com.ziwei.mall.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 * 订单超时取消
 */

@Component
public class OrderTimeOutCancelTask {
    private Logger LOGGER = LoggerFactory.getLogger(OrderTimeOutCancelTask.class);

    /**
     * 每半小时扫描一次，超过时间取消订单
     */
    @Scheduled(cron = "0 0/30 * ? * ?")
    private void cancelTimeOutOrder() {
        // TODO: 取消订单
        LOGGER.info("订单超时未支付已被取消");
    }
}
