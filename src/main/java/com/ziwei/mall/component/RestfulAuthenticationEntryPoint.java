package com.ziwei.mall.component;

import cn.hutool.json.JSONUtil;
import com.ziwei.mall.common.api.CommonResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name mallSpringboot
 * 未登录或token失效时，返回json
 */

@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().println(JSONUtil.parse(CommonResult.unauthorized(authException.getMessage())));
        response.getWriter().flush();
    }
}
