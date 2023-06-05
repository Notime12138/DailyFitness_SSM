package com.ziwei.dailyFitness.service;

import com.ziwei.dailyFitness.nosql.mongodb.MemberReadHistory;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/9
 * @name DailyFitnessSpringboot
 */

public interface MemberReadHistoryService {
    /**
     * 生成记录
     * @param memberReadHistory
     * @return
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 删除记录
     * @param ids
     * @return
     */
    int delete(List<String> ids);

    /**
     * 获取浏览记录
     * @param memberId
     * @return
     */
    List<MemberReadHistory> list(Long memberId);
}
