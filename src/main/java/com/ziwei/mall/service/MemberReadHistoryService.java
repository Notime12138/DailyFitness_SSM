package com.ziwei.mall.service;

import com.ziwei.mall.nosql.mongodb.MemberReadHistory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/9
 * @name mallSpringboot
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
