package com.ziwei.mall.service;

import com.ziwei.mall.common.api.CommonResult;

/**
 * @author Ziwei GONG
 * @date 2023/3/31
 * @name mallSpringboot
 */

public interface EmailService {
    /**
     * 向目标邮箱发送验证码
     * @param email 目标邮箱
     * @param code 验证码
     */
    CommonResult sendVerificationCode(String email);

    /**
     * 验证验证码是否正确
     * @param email
     * @param code
     * @return
     */
    CommonResult verifyCode(String email, String code);
}
