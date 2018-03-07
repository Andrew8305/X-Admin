package com.leaf.xadmin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.Role;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:38</p>
 * <p>version: 1.0</p>
 */
public interface IRoleService extends IService<Role> {
    /**
     * 查询用户角色列表
     *
     * @param name
     * @return
     */
    List<Role> queryUserRoles(String name);

    /**
     * 查询管理员角色列表
     *
     * @param name
     * @return
     */
    List<Role> queryAdminRoles(String name);

    /**
     * 添加角色
     *
     * @param role
     * @return
     */
    boolean addOne(Role role);

    /**
     * 批量添加角色
     *
     * @param roleList
     * @return
     */
    boolean addBatch(List<Role> roleList);

    /**
     * 查询角色列表
     *
     * @param page
     * @return
     */
    Page<Role> queryList(Page<Role> page);

    /**
     * 查询指定角色信息
     *
     * @param id
     * @return
     */
    Role queryOneById(Serializable id);

    /**
     * 查询指定名称角色
     *
     * @param name
     * @return
     */
    Role queryOneByName(String name);

    /**
     * 更新角色信息
     *
     * @param role
     * @return
     */
    boolean updateOneById(Role role);

}
