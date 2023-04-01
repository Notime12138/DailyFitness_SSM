package com.ziwei.mall.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 * 生成订单
 */

@Getter
@Setter
public class OrderParam {
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 收货地址id
     */
    private Long memberReceiveAddressId;
    /**
     * 优惠券id
     */
    private Long couponId;
    /**
     * 使用的积分数
     */
    private Integer useIntegration;
    /**
     * 支付方式
     */
    private Integer payType;
}

