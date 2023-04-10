package com.ziwei.mall.common.api;

import com.github.pagehelper.PageInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/27
 * @name mallSpringboot
 * 将PageHelper 分页后的List转化为分页信息
 */

@Setter
@Getter
public class CommonPage<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;

    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> res = new CommonPage<T>();
        PageInfo<T> pageInfo = new PageInfo<T>(list);
        res.setTotalPage(pageInfo.getPages());
        res.setPageNum(pageInfo.getPageNum());
        res.setPageSize(pageInfo.getPageSize());
        res.setTotal(pageInfo.getTotal());
        res.setList(pageInfo.getList());
        return res;
    }
}
