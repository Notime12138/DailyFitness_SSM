package com.ziwei.dailyFitness.config;

import com.ziwei.dailyFitness.common.util.JwtTokenUtil;
import com.ziwei.dailyFitness.component.JwtAuthenticationTokenFilter;
import com.ziwei.dailyFitness.component.RestfulAccessDeniedHandler;
import com.ziwei.dailyFitness.component.RestfulAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name DailyFitnessSpringboot
 * 用于管理配置后台管理员
 */

@Configuration
public class CommonSecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public RestfulAccessDeniedHandler restfulAccessDeniedHandler() {
        return new RestfulAccessDeniedHandler();
    }

    @Bean
    public RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint() {
        return new RestfulAuthenticationEntryPoint();
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

}
