package com.ziwei.dailyFitness.service.impl;

import com.ziwei.dailyFitness.dao.EsProductDao;
import com.ziwei.dailyFitness.nosql.elasticsearch.doc.EsProduct;
import com.ziwei.dailyFitness.nosql.elasticsearch.repository.EsProductRepository;
import com.ziwei.dailyFitness.service.EsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/2
 * @name DailyFitnessSpringboot
 */

@Service
public class EsProductServiceImpl implements EsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EsProductServiceImpl.class);

    @Autowired
    private EsProductDao esProductDao;
    @Autowired
    private EsProductRepository esProductRepository;

    @Override
    public int importAll() {
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(null);
        try {
            esProductRepository.saveAll(esProductList);
        } catch (Exception e) {
            if (!e.getMessage().contains("OK")) {
                LOGGER.info("商品录入失败");
                throw e;
            }
            LOGGER.info("商品录入成功");
        }
//        Iterator<EsProduct> iterator = esProductIterable.iterator();
//        int num = 0;
//        while (iterator.hasNext()) {
//            num++;
//            iterator.next();
//        }
//        LOGGER.info("成功录入了{}个商品", num);
        return esProductList.size();
    }

    @Override
    public void delete(Long id) {
        esProductRepository.deleteById(id);
        LOGGER.info("成功删除了商品{}", id);
    }

    @Override
    public EsProduct create(Long id) {
        EsProduct res = null;
        List<EsProduct> esProductList = esProductDao.getAllEsProductList(id);
        if (!esProductList.isEmpty()) {
            EsProduct esProduct = esProductList.get(0);
            res = esProductRepository.save(esProduct);
        }
        LOGGER.info("成功创建了商品{}", id);
        return res;
    }

    @Override
    public void delete(List<Long> ids) {
        if (!CollectionUtils.isEmpty(ids)) {
            List<EsProduct> esProductList = new ArrayList<>(ids.size());
            for (Long id : ids) {
                EsProduct esProduct = new EsProduct();
                esProduct.setId(id);
                esProductList.add(esProduct);
            }
            esProductRepository.deleteAll(esProductList);
            LOGGER.info("成功删除了指定商品");
        } else {
            LOGGER.info("无法删除不存在的商品");
        }
    }

    @Override
    public Page<EsProduct> search(String keyword, Integer pageNum, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNum, pageSize);
        LOGGER.info("正在搜索...");
        return esProductRepository.findByNameOrSubTitleOrKeywords(keyword, keyword, keyword, pageable);
    }
}
