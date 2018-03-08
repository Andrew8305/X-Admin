package com.leaf.xadmin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.auth.Permission;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:38</p>
 * <p>version: 1.0</p>
 */
public interface IPermissionService extends IService<Permission> {
    /**
     * 查询用户权限列表
     *
     * @param id
     * @return
     */
    List<Permission> queryUserPermissions(Serializable id);

    /**
     * 查询管理员权限列表
     *
     * @param id
     * @return
     */
    List<Permission> queryAdminPermissions(Serializable id);

    /**
     * 添加权限
     *
     * @param permission
     * @return
     */
    boolean addOne(Permission permission);

    /**
     * 批量添加权限
     *
     * @param permissionList
     * @return
     */
    boolean addBatch(List<Permission> permissionList);

    /**
     * 查询权限列表
     *
     * @param page
     * @return
     */
    Page<Permission> queryList(Page<Permission> page);

    /**
     * 查询指定权限信息
     *
     * @param id
     * @return
     */
    Permission queryOneById(Serializable id);

    /**
     * 查询指定名称权限
     *
     * @param name
     * @return
     */
    Permission queryOneByName(String name);

    /**
     * 更新权限信息
     *
     * @param role
     * @return
     */
    boolean updateOne(Permission role);

    /**
     * 删除权限
     *
     * @param id
     * @return
     */
    boolean deleteOne(Serializable id);
}
