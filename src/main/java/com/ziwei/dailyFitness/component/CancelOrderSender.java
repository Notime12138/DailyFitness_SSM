package com.ziwei.dailyFitness.component;

import com.ziwei.dailyFitness.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name DailyFitnessSpringboot
 * 发送取消订单的消息
 * 将消息加至mall.order.cancel.ttl队列中
 */

@Component
public class CancelOrderSender {
    private static Logger LOGGER = LoggerFactory.getLogger(CancelOrderSender.class);

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(Long orderId, final long delay) {
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_TTL_ORDER_CANCEL.getExchange(), QueueEnum.QUEUE_TTL_ORDER_CANCEL.getRouteKey(), orderId, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                // 给消息设置延迟毫秒数
                message.getMessageProperties().setExpiration(String.valueOf(delay));
                return message;
            }
        });
        LOGGER.info("Send delayed message orderId:{}",orderId);
    }
}
