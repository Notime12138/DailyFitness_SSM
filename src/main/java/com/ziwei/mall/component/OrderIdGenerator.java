package com.ziwei.mall.component;

import com.ziwei.mall.dto.OrderParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 */

public class OrderIdGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderIdGenerator.class);

    public static long generateOrderId(OrderParam orderParam) {
        long currentTime = System.currentTimeMillis();
        int random = (int)(Math.random() * 10000);
        long userId = orderParam.getUserId();
        LOGGER.info("成功生成订单号");
        return currentTime * 10000 + random + userId;
    }
}
