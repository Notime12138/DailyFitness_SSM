package com.ziwei.mall.service;

import com.ziwei.mall.nosql.elasticsearch.doc.EsProduct;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name mallSpringboot
 * 商品搜索功能
 */

public interface EsProductService {
    /**
     * 导入数据到ES
     */
    int importAll();

    /**
     * 根据id删除商品
     */
    void delete(Long id);

    /**
     * 根据id创建商品
     */
    EsProduct create(Long id);

    /**
     * 批量删除商品
     */
    void delete(List<Long> ids);

    /**
     * 根据关键字搜索
     */
    Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize);
}
