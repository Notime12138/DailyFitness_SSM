package com.ziwei.mall.config;

import com.ziwei.mall.component.JwtAuthenticationTokenFilter;
import com.ziwei.mall.component.RestfulAccessDeniedHandler;
import com.ziwei.mall.component.RestfulAuthenticationEntryPoint;
import com.ziwei.mall.dto.AdminUserDetails;
import com.ziwei.mall.mbg.model.UmsAdmin;
import com.ziwei.mall.mbg.model.UmsPermission;
import com.ziwei.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name mallSpringboot
 * @EnableGlobalMethodSecurity(prePostEnabled = true)
 * 允许在方法执行之前或之后进行访问控制检查，以决定是否允许调用该方法。这样可以在方法级别精细地控制访问权限，而不必在每个请求上进行授权检查。
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UmsAdminService umsAdminService;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf()// CSRF令牌是一段随机生成的字符串，与用户会话相关联，每个请求都需要携带此令牌，服务器验证此令牌才会执行请求。
                .disable()// 不需要通过登录验证
                .sessionManagement()// 基于token，不使用session
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/swagger-resources/**",
                        "/api-docs/**"
                ) // 不需要通过验证的文件
                .permitAll()
                .antMatchers("/admin/login", "/admin/register")
                .permitAll()
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
                .anyRequest()
                .authenticated();
        // 禁用缓存
        http.headers().cacheControl();
        // 添加JWT filter
        http.addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果的返回（json）
        http.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restfulAuthenticationEntryPoint);
    }

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() {
        return new JwtAuthenticationTokenFilter();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService())
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public UserDetailsService userDetailsService() {
        // 获取登录用户信息
        return username -> {
            UmsAdmin umsAdmin = umsAdminService.getAdminByUserName(username);
            if (umsAdmin != null) {
                List<UmsPermission> permissionList = umsAdminService.getPermissionList(umsAdmin.getId());
                return new AdminUserDetails(umsAdmin, permissionList);
            }
            throw new UsernameNotFoundException("USER DOES NOT EXIST");
        };
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
