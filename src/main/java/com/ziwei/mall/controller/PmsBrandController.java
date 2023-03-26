package com.ziwei.mall.controller;

import com.ziwei.mall.mbg.model.PmsBrand;
import com.ziwei.mall.service.PmsBrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/26
 * @name mallSpringboot
 * Brand Controller
 */

@Controller
@RequestMapping("/brand")
public class PmsBrandController {
    @Autowired
    private PmsBrandService pmsBrandService;

    // LOGGER 作用：
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsBrandController.class);

//    @RequestMapping(value = "listALL", method = RequestMethod.GET)
//    @ResponseBody
//    public CommonResult<List<PmsBrand>> getBrandList() {
//        return CommonResult.success(pmsBrandService.listAllBrand());
//    }
}
