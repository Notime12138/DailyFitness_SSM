package com.ziwei.mall.dao;

import com.ziwei.mall.nosql.elasticsearch.doc.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name mallSpringboot
 * 搜索系统中的商品管理自定义Dao
 */

public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
