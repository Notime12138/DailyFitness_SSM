package com.ziwei.dailyFitness.service;

import com.ziwei.dailyFitness.common.api.CommonResult;

/**
 * @author Ziwei GONG
 * @date 2023/3/29
 * @name DailyFitnessSpringboot
 * 会员管理（手机号）
 */

public interface UmsMemberService {
    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
