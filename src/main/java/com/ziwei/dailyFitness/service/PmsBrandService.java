package com.ziwei.dailyFitness.service;

import com.ziwei.dailyFitness.mbg.model.PmsBrand;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name DailyFitnessSpringboot
 */

public interface PmsBrandService {

    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand pmsBrand);

    int updateBrand(Long id, PmsBrand pmsBrand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}
