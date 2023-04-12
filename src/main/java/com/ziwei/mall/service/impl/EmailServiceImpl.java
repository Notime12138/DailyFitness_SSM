package com.ziwei.mall.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.common.util.VerificationCodeUtil;
import com.ziwei.mall.service.EmailService;
import com.ziwei.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

/**
 * @author Ziwei GONG
 * @date 2023/4/1
 * @name mallSpringboot
 */

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    JavaMailSender javaMailSender;
    @Autowired
    RedisService redisService;
    VerificationCodeUtil verificationCodeUtil = new VerificationCodeUtil();
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private long AUTH_CODE_EXPIRE_SECONDS;
    @Value("${spring.mail.username}")
    private String MAIL_SENDER;

    @Override
    public CommonResult sendVerificationCode(String email) {
        // TODO:需要验证邮箱的格式
        StringBuilder stringBuilder = verificationCodeUtil.generateVerificationCode();
        // 验证码绑定邮箱，并且储存到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + email, stringBuilder.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + email, AUTH_CODE_EXPIRE_SECONDS);
        // 向目标邮箱发送验证码
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(MAIL_SENDER);
        mailMessage.setTo(email);
        mailMessage.setSubject("Verification Code");
        mailMessage.setText("正在测试邮箱验证码功能，以下是你的验证码：\n" + stringBuilder + "\n有效时间2分钟！");
        javaMailSender.send(mailMessage);
        // 检验是否发送成功
        return CommonResult.success(stringBuilder.toString(), "验证码发送成功");
    }

    @Override
    public CommonResult verifyCode(String email, String authCode) {
        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + email);
        return verificationCodeUtil.verifyCode(email, authCode, realAuthCode);
    }
}
