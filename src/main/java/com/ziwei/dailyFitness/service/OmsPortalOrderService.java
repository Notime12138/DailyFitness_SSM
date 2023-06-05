package com.ziwei.dailyFitness.service;

import com.ziwei.dailyFitness.common.api.CommonResult;
import com.ziwei.dailyFitness.dto.OrderParam;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name DailyFitnessSpringboot
 */

public interface OmsPortalOrderService {

    /**
     * 根据调教信息生成订单
     * @Transactional 在方法执行后根据执行结果决定提交或者回滚事务。如果被注解的方法抛出了异常，则事务会回滚；如果方法执行成功，则事务会被提交。
     */
    @Transactional
    CommonResult generateOrder(OrderParam orderParam);

    /**
     * 取消订单
     */
    @Transactional
    void cancelOrder(Long orderId);


}
