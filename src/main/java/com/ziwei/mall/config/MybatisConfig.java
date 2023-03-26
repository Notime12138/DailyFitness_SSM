package com.ziwei.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name mallSpringboot
 * Mybatis config file
 */


@Configuration
@MapperScan("com.ziwei.mall.mbg.mapper")
public class MybatisConfig {

}
