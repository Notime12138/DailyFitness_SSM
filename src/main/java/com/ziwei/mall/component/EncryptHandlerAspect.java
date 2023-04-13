package com.ziwei.mall.component;

import com.ziwei.mall.annotation.EncryptField;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Objects;

/**
 * @author Ziwei GONG
 * @date 2023/4/12
 * @name mallSpringboot
 */

@Slf4j
@Aspect
@Component
public class EncryptHandlerAspect {
    @Autowired
    private StringEncryptor stringEncryptor;

    @Pointcut("@annotation(com.ziwei.mall.annotation.EncryptMethod)")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint joinPoint) {
        /**
         * 加密
         */
        encrypt(joinPoint);
        /**
         * 解密
         */
        Object decrypt = decrypt(joinPoint);
        return decrypt;
    }

    /**
     * 将被标记的字段加密
     * @param joinPoint
     */
    private void encrypt(ProceedingJoinPoint joinPoint) {
        try {
            Object[] objects = joinPoint.getArgs();
            if (objects.length != 0) {
                for (Object o : objects) {
                    if (o instanceof String) {
                        encryptValue(o);
                    } else {
                        handler(o, "encrypt");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    private Object decrypt(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            Object object = joinPoint.proceed();
            if (object != null) {
                if (object instanceof String) {
                    decryptValue(object);
                } else {
                    result = handler(object, "decrypt");
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 处理需要加密的复合数据类型
     * @param object
     * @param type
     * @return
     * @throws IllegalAccessException
     */
    private Object handler(Object object, String type) throws IllegalAccessException {
        if (Objects.isNull(object)) {
            return null;
        }
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            boolean hasSecureField = field.isAnnotationPresent(EncryptField.class);
            if (hasSecureField) {
                field.setAccessible(true);
                String realValue = (String) field.get(object);
                String value;
                // 判断是要解密还是加密
                if ("decrypt".equals(type)) {
                    value = stringEncryptor.decrypt(realValue);
                } else {
                    value = stringEncryptor.encrypt(realValue);
                }
                field.set(object, value);
            }
        }
        return object;
    }

    private String encryptValue(Object realValue) {
        String value = null;
        try {
            value = stringEncryptor.encrypt(String.valueOf(realValue));
        } catch (Exception e) {
            return value;
        }
        return value;
    }


    private String decryptValue(Object realValue) {
        String value = String.valueOf(realValue);
        try {
            value = stringEncryptor.decrypt(value);
        } catch (Exception e) {
            return value;
        }
        return value;
    }
}
