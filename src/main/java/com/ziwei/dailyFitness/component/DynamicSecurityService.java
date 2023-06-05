package com.ziwei.dailyFitness.component;

import org.springframework.security.access.ConfigAttribute;

import java.util.Map;

/**
 * @author Ziwei GONG
 * @date 2023/5/1
 * @name DailyFitnessSpringboot
 * 加载后台权限规则（资源）
 */

public interface DynamicSecurityService {
    /**
     * 用于取得接口的所需的权限（资源）
     * @return
     */
    Map<String, ConfigAttribute> loadDataSource();
}
