package com.ziwei.mall.config;

import com.ziwei.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;

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
}
