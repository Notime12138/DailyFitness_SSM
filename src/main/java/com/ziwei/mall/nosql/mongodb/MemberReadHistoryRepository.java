package com.ziwei.mall.nosql.mongodb;


import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/9
 * @name mallSpringboot
 * 会员操作记录操作
 */

public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory,String> {
    /**
     * 获取某个会员的历史记录
     * @param memberId 会员id
     * @return
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
