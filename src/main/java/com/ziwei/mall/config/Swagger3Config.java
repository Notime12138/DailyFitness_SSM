package com.ziwei.mall.config;

import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ziwei GONG
 * @date 2023/3/28
 * @name mallSpringboot
 * Swagger UI Api文档生成器 配置类
 * Swagger 2: http://localhost:8080/swagger-ui.html
 * Swagger 3: http://localhost:8080/swagger-ui/index.html
 */

@Configuration
//@EnableSwagger2
@EnableOpenApi
public class Swagger3Config {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //为当前包下Controller生成Api文档
                .apis(RequestHandlerSelectors.basePackage("com.ziwei.mall.controller"))
//              在 apis 方法中，可以使用 RequestHandlerSelectors 类中的静态方法指定筛选规则，常用的方法包括：
//                  any()：任何 API 接口都匹配。
//                  none()：不匹配任何 API 接口。
//                  basePackage(String basePackage)：匹配指定包中的 API 接口。
//                  withClassAnnotation(Class<? extends Annotation> annotation)：匹配带有指定注解的 API 接口。
//                  withMethodAnnotation(Class<? extends Annotation> annotation)：匹配带有指定注解的 API 方法。
//                为有@ApiOperation注释的方法生成Api文档
//                  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
//              在 paths 方法中，可以使用 PathSelectors 类中的静态方法指定扫描范围，常用的方法包括：
//                  any()：扫描所有 API 接口。
//                  none()：不扫描任何 API 接口。
//                  ant(String antPattern)：按照 Ant 风格的路径表达式匹配 API 接口。
//                  regex(String regex)：按照正则表达式匹配 API 接口。
                .build()
                // 登录认证
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger UI demo for mall")
                .description("Springboot Project following mall")
                .version("1.0")
                .build();
    }

    private List<SecurityScheme> securitySchemes() {
        // 设置请求头信息
        List<SecurityScheme> res = new ArrayList<>();
        res.add(new ApiKey("Authorization", "Authorization", "header"));
        // 在Swagger 3中，securitySchemes配置只接受SecurityScheme类型的安全方案，因此当你试图使用ApiKey时，就会提示类型不兼容的错误。
        res = res.stream().map(apiKey -> (SecurityScheme) apiKey).collect(Collectors.toList());
        return res;
    }

    private List<SecurityContext> securityContexts() {
        // 设置需要登录的路径
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/brand/.*"));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex) {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
//                .forPaths(PathSelectors.regex(pathRegex))
                .operationSelector(operationContext -> operationContext.requestMappingPattern().matches("pathRegex"))
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        List<SecurityReference> res = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        res.add(new SecurityReference("Authorization", authorizationScopes));
        return res;
    }


    //Swagger 3
    @Bean
    public static BeanPostProcessor springfoxHandlerProviderBeanPostProcessor() {
        return new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) {
                if (bean instanceof WebMvcRequestHandlerProvider) {
                    customizeSpringfoxHandlerMappings(getHandlerMappings(bean));
                }
                return bean;
            }

            private <T extends RequestMappingInfoHandlerMapping> void customizeSpringfoxHandlerMappings(List<T> mappings) {
                List<T> copy = mappings.stream().filter(mapping -> mapping.getPatternParser() == null).collect(Collectors.toList());
                mappings.clear();
                mappings.addAll(copy);
            }

            @SuppressWarnings("unchecked")
            private List<RequestMappingInfoHandlerMapping> getHandlerMappings(Object bean) {
                try {
                    Field field = ReflectionUtils.findField(bean.getClass(), "handlerMappings");
                    field.setAccessible(true);
                    return (List<RequestMappingInfoHandlerMapping>) field.get(bean);
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    throw new IllegalStateException(e);
                }
            }
        };
    }
}
