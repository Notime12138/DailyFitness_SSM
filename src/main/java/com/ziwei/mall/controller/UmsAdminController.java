package com.ziwei.mall.controller;

import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.dto.UmsAdminLoginParam;
import com.ziwei.mall.mbg.model.UmsAdmin;
import com.ziwei.mall.mbg.model.UmsPermission;
import com.ziwei.mall.service.UmsAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.CRC32;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name mallSpringboot
 * 管理员管理
 */

@Controller
@Api(tags = "管理员管理")
@RequestMapping("/admin")
public class UmsAdminController {
    @Autowired
    private UmsAdminService umsAdminService;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    @ResponseBody
    public CommonResult<UmsAdmin> register(@RequestBody UmsAdmin umsAdminParam, BindingResult bindingResult) {
        UmsAdmin umsAdmin = umsAdminService.register(umsAdminParam);
        if (umsAdmin == null) {
            CommonResult.failed();
        }
        return CommonResult.success(umsAdmin);
    }

    @ApiOperation(value = "登陆以后返回token")
    @PostMapping(value = "/login")
    @ResponseBody
    public CommonResult login(@RequestBody UmsAdminLoginParam umsAdminLoginParam, BindingResult bindingResult) {
        String token = umsAdminService.login(umsAdminLoginParam.getUsername(), umsAdminLoginParam.getPassword());
        if (token == null) {
            return CommonResult.validatedFailed("Username or Password incorrect");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return CommonResult.success(tokenMap);
    }

    @ApiOperation("获取用户的权限")
    @GetMapping(value = "/permission/{adminId}")
    @ResponseBody
    public CommonResult<List<UmsPermission>> getPermissionList(@PathVariable Long adminId) {
        List<UmsPermission> permissionList = umsAdminService.getPermissionList(adminId);
        return CommonResult.success(permissionList);
    }


}
