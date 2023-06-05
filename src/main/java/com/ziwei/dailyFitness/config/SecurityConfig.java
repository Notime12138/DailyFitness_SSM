package com.ziwei.dailyFitness.config;

import com.ziwei.dailyFitness.component.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name DailyFitnessSpringboot
 * @EnableGlobalMethodSecurity(prePostEnabled = true)
 * 允许在方法执行之前或之后进行访问控制检查，以决定是否允许调用该方法。这样可以在方法级别精细地控制访问权限，而不必在每个请求上进行授权检查。
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;
    @Autowired
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;
    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;
    @Autowired
    private RestfulAuthenticationEntryPoint restfulAuthenticationEntryPoint;
    @Autowired(required = false)
    private DynamicSecurityService dynamicSecurityService;
    @Autowired(required = false)
    private DynamicSecurityFilter dynamicSecurityFilter;


    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        for (String url : ignoreUrlsConfig.getUrls()) {
            httpSecurity.authorizeRequests().antMatchers(url).permitAll();
        }
        httpSecurity
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
                        "/**/*.js"
                )// 不需要通过验证的文件
                .permitAll()
                .antMatchers("/swagger-resources/**", "/swagger-ui/", "/**/v2/api-docs")
                .permitAll()
                .antMatchers("/admin/login", "/admin/register", "/email/**")
                .permitAll() // 登录和注册不需要权限
                .antMatchers(HttpMethod.OPTIONS)
                .permitAll()
//                .antMatchers("/**")
//                .permitAll()// 测试全通过
                .anyRequest()
                .authenticated();
        // 禁用缓存
        httpSecurity.headers().cacheControl();
        // 添加JWT filter
        httpSecurity.addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class);
        // 添加自定义未授权和未登录结果的返回（json）
        httpSecurity.exceptionHandling()
                .accessDeniedHandler(restfulAccessDeniedHandler)
                .authenticationEntryPoint(restfulAuthenticationEntryPoint);
//        TODO:统一httpSecurity对象
//        if (dynamicSecurityService != null) {
//            httpSecurity.addFilterBefore(dynamicSecurityFilter, FilterSecurityInterceptor.class);
//        }
        return httpSecurity.build();
    }

}
