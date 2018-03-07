package com.leaf.xadmin.mapper.auth;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.auth.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:33</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface RoleMapper extends BaseMapper<Role> {
    /**
     * 查询用户角色列表
     *
     * @param name
     * @return
     */
    List<Role> selectUserRoles(String name);

    /**
     * 查询管理员角色列表
     *
     * @param name
     * @return
     */
    List<Role> selectAdminRoles(String name);

    /**
     * 删除管理员角色相关依赖
     *
     * @param id
     * @return
     */
    Integer deleteAdminRoleDeps(Serializable id);

    /**
     * 删除用户角色相关依赖
     *
     * @param id
     * @return
     */
    Integer deleteUserRoleDeps(Serializable id);

    /**
     * 删除资源角色相关依赖
     *
     * @param id
     * @return
     */
    Integer deleteResourceRoleDeps(Serializable id);

    /**
     * 删除权限角色依赖
     *
     * @param id
     * @return
     */
    Integer deletePermRoleDeps(Serializable id);
}
