package com.ziwei.mall.common.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/27
 * @name mallSpringboot
 * 将PageHelper 分页后的List转化为分页信息
 */

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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }


}
