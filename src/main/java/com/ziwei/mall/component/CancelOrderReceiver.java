package com.ziwei.mall.component;

import com.ziwei.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 * 从mall.order.cancel中获得消息
 */

@Component
@RabbitListener(queues = "mall.order.cancel")
public class CancelOrderReceiver {
    private Logger LOGGER = LoggerFactory.getLogger(CancelOrderReceiver.class);
    @Autowired
    private OmsPortalOrderService portalOrderService;

    @RabbitHandler
    public void handle(Long orderId) {
        LOGGER.info("收到延迟消息的订单号:{}", orderId);
        portalOrderService.cancelOrder(orderId);
    }
}
