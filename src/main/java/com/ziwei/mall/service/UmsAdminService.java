package com.ziwei.mall.service;

import com.ziwei.mall.mbg.model.UmsAdmin;
import com.ziwei.mall.mbg.model.UmsPermission;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name mallSpringboot
 * 管理员相关服务（权限登录）
 */

public interface UmsAdminService {
    /**
     * 根据用户名获取后台管理员
     */
    UmsAdmin getAdminByUserName(String userName);

    /**
     * 注册
     */
    UmsAdmin register(UmsAdmin umsAdminParam);

    /**
     * 登录
     * @return 生成的JWT的token
     */
    String login(String userName, String password);

    /**
     * 加载用户信息
     * @return
     */
    UserDetails loadUserByUsername(String userName);


    /**
     * 获取用户的权限
     */
    List<UmsPermission> getPermissionList(Long adminId);
}
