package com.ziwei.dailyFitness.dao;

import com.ziwei.dailyFitness.mbg.model.UmsPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author Ziwei GONG
 * @date 2023/3/30
 * @name DailyFitnessSpringboot
 * 后台角色和角色权限管理
 */

public interface UmsAdminRoleRelationDao {
    /**
     * 通过角色返回角色的对应权限
     * @param adminId
     * @return
     */
    List<UmsPermission> getPermissionList(@Param("adminId") Long adminId);
}
