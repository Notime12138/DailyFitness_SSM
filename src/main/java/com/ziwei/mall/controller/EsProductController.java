package com.ziwei.mall.controller;

import com.ziwei.mall.common.api.CommonPage;
import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.nosql.elasticsearch.doc.EsProduct;
import com.ziwei.mall.service.EsProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.management.ValueExp;
import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/4/3
 * @name mallSpringboot
 */

@Controller
@Api(tags = "商品搜索管理")
@RequestMapping("/esProduct")
public class EsProductController {
    @Autowired
    private EsProductService esProductService;

    @ApiOperation(value = "将商品信息导入到ES中")
    @PostMapping(value = "/importAll")
    @ResponseBody
    public CommonResult<Integer> importAllList() {
        int count = esProductService.importAll();
        return CommonResult.success(count);
    }

    @ApiOperation(value = "根据id删除商品")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public CommonResult<Object> delete(@PathVariable Long id) {
        esProductService.delete(id);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id批量删除商品")
    @PostMapping(value = "/delete/batch")
    @ResponseBody
    public CommonResult<Object> delete(@RequestParam("ids") List<Long> ids) {
        esProductService.delete(ids);
        return CommonResult.success(null);
    }

    @ApiOperation(value = "根据id创建商品")
    @PostMapping(value = "/create/{id}")
    @ResponseBody
    public CommonResult<EsProduct> create(@PathVariable Long id) {
        EsProduct esProduct = esProductService.create(id);
        if (esProduct != null) {
            return CommonResult.success(esProduct);
        } else {
            return CommonResult.failed();
        }
    }

    @ApiOperation(value = "搜索")
    @GetMapping(value = "/search/simple")
    @ResponseBody
    public CommonResult<CommonPage<EsProduct>> search(@RequestParam(required = false) String keyword,
                                                      @RequestParam(required = false, defaultValue = "0") Integer pageNum,
                                                      @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        Page<EsProduct> esProductPage = esProductService.search(keyword,pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(esProductPage.getContent()));
    }
}
