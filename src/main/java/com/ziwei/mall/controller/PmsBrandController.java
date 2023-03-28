package com.ziwei.mall.controller;

import com.ziwei.mall.common.api.CommonPage;
import com.ziwei.mall.common.api.CommonResult;
import com.ziwei.mall.mbg.model.PmsBrand;
import com.ziwei.mall.service.PmsBrandService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name mallSpringboot
 * Brand Controller
 */

@Api(tags = "PmsBrandController")
@RestController
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    /**
     * LOGGER.debug方法会将指定的日志信息输出到日志系统中。
     * 当应用程序开发完成并进入生产环境时，应将日志级别设置为INFO或以上，以便减少日志输出对系统性能的影响。
     * 避免在生产环境中输出敏感信息，如密码、密钥等，建议在日志输出中使用占位符或敏感信息脱敏等技术，以保证应用程序的安全性。
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

    @ApiOperation(value = "获取品牌列表", notes = "获取所有品牌的列表")
    @RequestMapping(value = "/listALL", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult<List<PmsBrand>> getBrandList() {
        return CommonResult.success(pmsBrandService.listAllBrand());
    }


    @ApiOperation("添加品牌")
    @PostMapping(value = "/create")
    @ResponseBody
    public CommonResult createBrand(@RequestBody PmsBrand pmsBrand) {
        CommonResult commonResult;
        int count = pmsBrandService.createBrand(pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("createBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("插入失败");
            LOGGER.debug("createBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation(value = "更新品牌", notes = "根据id更新品牌信息")
    @PostMapping(value = "/update/{id}")
    @ResponseBody
    public CommonResult updateBrand(@PathVariable("id") Long id, @RequestBody PmsBrand pmsBrand, BindingResult bindingResult) {
        CommonResult commonResult;
        int count = pmsBrandService.updateBrand(id, pmsBrand);
        if (count == 1) {
            commonResult = CommonResult.success(pmsBrand);
            LOGGER.debug("ubdateBrand success:{}", pmsBrand);
        } else {
            commonResult = CommonResult.failed("更新失败");
            LOGGER.debug("updateBrand failed:{}", pmsBrand);
        }
        return commonResult;
    }

    @ApiOperation(value = "删除品牌", notes = "根据id删除品牌")
    @DeleteMapping(value = "/delete/{id}")
    @ResponseBody
    public CommonResult deleteBrand(@PathVariable("id") Long id) {
        int count = pmsBrandService.deleteBrand(id);
        if (count == 1) {
            LOGGER.debug("deleteBrand success :id={}", id);
            return CommonResult.success(null);
        } else {
            LOGGER.debug("deleteBrand failed:id={}", id);
            return CommonResult.failed("删除失败");
        }
    }

    @ApiOperation(value = "查询品牌列表", notes = "分页查询")
    @GetMapping(value = "/list")
    @ResponseBody
    public CommonResult<CommonPage<PmsBrand>> listBrand(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,@RequestParam(value = "pageSize",defaultValue = "3")Integer pageSize) {
        List<PmsBrand> brandList = pmsBrandService.listBrand(pageNum,pageSize);
        return CommonResult.success(CommonPage.restPage(brandList));
    }

    @ApiOperation(value = "查询指定品牌",notes = "获取指定id的品牌信息")
    @GetMapping(value = "/{id}")
    @ResponseBody
    public CommonResult<PmsBrand> brand(@PathVariable("id") Long id) {
        return CommonResult.success(pmsBrandService.getBrand(id));
    }
}
