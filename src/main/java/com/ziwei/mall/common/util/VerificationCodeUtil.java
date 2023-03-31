package com.ziwei.mall.common.util;

import com.alibaba.druid.util.StringUtils;
import com.ziwei.mall.common.api.CommonResult;

import java.util.Random;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 */

public class VerificationCodeUtil {
    public StringBuilder generateVerificationCode () {
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            stringBuilder.append(random.nextInt(10));
        }
        return stringBuilder;
    }

    public CommonResult verifyCode(String email, String authCode, String realAuthCode) {
        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }
        boolean res = authCode.equals(realAuthCode);
        if (res) {
            return CommonResult.success(null, "验证码验证成功");
        } else {
            return CommonResult.failed("验证码不成功");
        }
    }
}
