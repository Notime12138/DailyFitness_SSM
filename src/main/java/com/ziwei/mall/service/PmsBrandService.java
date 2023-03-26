package com.ziwei.mall.service;

import com.ziwei.mall.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name mallSpringboot
 */

public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id, PmsBrand pmsBrand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
