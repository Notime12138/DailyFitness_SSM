package com.ziwei.mall.config;

import com.ziwei.mall.component.DynamicSecurityService;
import com.ziwei.mall.mbg.model.UmsPermission;
import com.ziwei.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name mallSpringboot
 * 用于管理配置后台管理员
 */

@Configuration
public class AdminSecurityConfig {
    @Autowired
    private UmsAdminService umsAdminService;

    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> umsAdminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> configAttributeMap = new ConcurrentHashMap<>();
            // TODO:获取配置好的权限管理数据（资源列表）
//            List<UmsPermission> resources = umsAdminService.getPermissionList((long) 1);
//            for (UmsPermission umsPermission : resources) {
//                configAttributeMap.put(umsPermission.getUri(), new SecurityConfig(umsPermission.getId() + ":" + umsPermission.getName()));
//            }
            return configAttributeMap;
        };
    }
}
