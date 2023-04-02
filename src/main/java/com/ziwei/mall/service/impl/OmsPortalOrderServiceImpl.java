package com.ziwei.mall.service.impl;

import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.component.CancelOrderSender;
import com.ziwei.mall.component.OrderIdGenerator;
import com.ziwei.mall.dto.OrderParam;
import com.ziwei.mall.service.OmsPortalOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 */

@Service
public class OmsPortalOrderServiceImpl implements OmsPortalOrderService {
    private static Logger LOGGER = LoggerFactory.getLogger(OmsPortalOrderServiceImpl.class);
    @Autowired
    private CancelOrderSender cancelOrderSender;

    @Override
    public CommonResult generateOrder(OrderParam orderParam) {
        // TODO:下单
        LOGGER.info("订单生成成功");
        // 下单完成后生成延迟消息和OrderId，如果用户没有及时付款，就根据OrderId取消订单
        sendDelayMessageCancelOrder(OrderIdGenerator.generateOrderId(orderParam));
        return CommonResult.success(null, "下单成功，请及时完成支付");
    }

    @Override
    public void cancelOrder(Long orderId) {
        // TODO: 取消订单
        LOGGER.info("由于一些原因，订单:{}已被取消", orderId);
    }

    private void sendDelayMessageCancelOrder(Long orderId) {
        // 订单超时时间 (此处30s)
        long delay = 30 * 1000;
        // 发送延迟消息
        cancelOrderSender.sendMessage(orderId, delay);
    }
}
