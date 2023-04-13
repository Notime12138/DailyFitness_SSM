package com.ziwei.mall.annotation;

import java.lang.annotation.*;

/**
 * @author Ziwei GONG
 * @date 2023/4/12
 * @name mallSpringboot
 * 用于注释需要加密的字段
 */

@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
    String[] value() default "";
}
