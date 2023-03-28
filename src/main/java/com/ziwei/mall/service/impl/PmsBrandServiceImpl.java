package com.ziwei.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.ziwei.mall.mbg.mapper.PmsBrandMapper;
import com.ziwei.mall.mbg.model.PmsBrand;
import com.ziwei.mall.mbg.model.PmsBrandExample;
import com.ziwei.mall.service.PmsBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name mallSpringboot
 * PmsBrandService实现类
 */

@Service
public class PmsBrandServiceImpl implements PmsBrandService {
    @Autowired
    private PmsBrandMapper pmsBrandMapper;

//    Springboot 3.x中使用创建了SqlSessionFactory的Mapper，失败。
//    private final PmsBrandMapper pmsBrandMapper;
//
//    @Autowired
//    public PmsBrandServiceImpl(PmsBrandMapper pmsBrandMapper) {
//        this.pmsBrandMapper = pmsBrandMapper;
//    }

    @Override
    public List<PmsBrand> listAllBrand() {
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public int createBrand(PmsBrand pmsBrand) {
        return pmsBrandMapper.insertSelective(pmsBrand);
    }

    @Override
    public int updateBrand(Long id, PmsBrand pmsBrand) {
        pmsBrand.setId(id);
        return pmsBrandMapper.updateByPrimaryKeySelective(pmsBrand);
    }

    @Override
    public int deleteBrand(Long id) {
        return pmsBrandMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<PmsBrand> listBrand(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return pmsBrandMapper.selectByExample(new PmsBrandExample());
    }

    @Override
    public PmsBrand getBrand(Long id) {
        return pmsBrandMapper.selectByPrimaryKey(id);
    }
}
