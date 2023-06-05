package com.ziwei.dailyFitness.dao;

import com.ziwei.dailyFitness.nosql.elasticsearch.doc.EsProduct;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name DailyFitnessSpringboot
 * 搜索系统中的商品管理自定义Dao
 */

public interface EsProductDao {
    List<EsProduct> getAllEsProductList(@Param("id") Long id);
}
