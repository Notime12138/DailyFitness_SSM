package com.ziwei.dailyFitness.controller;

import com.ziwei.dailyFitness.common.api.CommonResult;
import com.ziwei.dailyFitness.service.UmsMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author Ziwei GONG
 * @date 2023/3/29
 * @name DailyFitnessSpringboot
 * 会员登录注册Controller
 */

@Controller
@Api(tags = "会员登录注册管理")
@RequestMapping("/sso")
public class UmsMemberController {
    @Autowired
    private UmsMemberService umsMemberService;

    @ApiOperation("获取验证码")
    @GetMapping(value = "/getAuthCode")
    @ResponseBody
    public CommonResult getAuthCode(@ApiParam(value = "手机号") @RequestParam String telephone) {
        return umsMemberService.generateAuthCode(telephone);
    }

    @ApiOperation("校验验证码")
    @PostMapping(value = "/verifyAuthCode")
    @ResponseBody
    public CommonResult updatePassword(@ApiParam(value = "手机号") @RequestParam String telephone, @ApiParam(value = "验证码") @RequestParam String authCode) {
        return umsMemberService.verifyAuthCode(telephone, authCode);
    }

}
