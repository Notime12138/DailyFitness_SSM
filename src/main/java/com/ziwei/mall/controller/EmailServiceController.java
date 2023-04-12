package com.ziwei.mall.controller;

import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.service.EmailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ziwei GONG
 * @date 2023/4/12
 * @name mallSpringboot
 */

@Controller
@Api(tags = "邮件发送管理")
@RequestMapping("/email")
public class EmailServiceController {
    @Autowired
    EmailService emailService;

    @ApiOperation(value = "发送邮箱验证码")
    @PostMapping(value = "/sendCode")
    @ResponseBody
    public CommonResult sendEmailCode(@RequestParam("email") String email) {
        return emailService.sendVerificationCode(email);
    }

    @ApiOperation(value = "验证验证码")
    @PostMapping(value = "/verifyCode")
    @ResponseBody
    public CommonResult verifyEmailCode(@RequestParam("email") String email, @RequestParam("code") String code) {
        return emailService.verifyCode(email,code);
    }
}
