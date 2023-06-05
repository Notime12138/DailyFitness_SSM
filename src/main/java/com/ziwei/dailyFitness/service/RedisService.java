package com.ziwei.dailyFitness.service;

/**
 * @author Ziwei GONG
 * @date 2023/3/29
 * @name DailyFitnessSpringboot
 * redis 的对象和数组都以json形式进行储存
 * 使用Redis Templte操作Redis
 */

public interface RedisService {
    /**
     * 存储数据
     */
    void set(String key, String value);

    /**
     * 获取数据
     */
    String get(String key);

    /**
     * 设置超时时间
     */
    boolean expire(String key, long expire);

    /**
     * 删除数据
     */
    void remove(String key);

    /**
     * 自增操作
     *
     * @parm delta自增步长
     */
    Long increment(String key, long delta);
}
