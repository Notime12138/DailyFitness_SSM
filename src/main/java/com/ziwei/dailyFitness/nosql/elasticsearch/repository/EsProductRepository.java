package com.ziwei.dailyFitness.nosql.elasticsearch.repository;

import com.ziwei.dailyFitness.nosql.elasticsearch.doc.EsProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;


/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name DailyFitnessSpringboot
 * ElasticSearch 操作类
 */

public interface EsProductRepository extends ElasticsearchRepository<EsProduct, Long> {
    /**
     * 搜索查询字段
     * @param name
     * @param subTitle
     * @param keywords
     * @param page
     * @return
     */
    Page<EsProduct> findByNameOrSubTitleOrKeywords(String name, String subTitle, String keywords, Pageable page);
}
