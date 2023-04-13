package com.ziwei.mall.annotation;

import java.lang.annotation.*;

/**
 * @author Ziwei GONG
 * @date 2023/4/12
 * @name mallSpringboot
 * 用于注释使用需要加密字段的方法上
 */

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptMethod {
    String[] type() default "encrypt";
}
