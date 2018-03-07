package com.leaf.xadmin.mapper.auth;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.auth.Permission;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:34</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface PermissionMapper extends BaseMapper<Permission> {
    /**
     * 查询用户权限列表
     *
     * @param name
     * @return
     */
    List<Permission> selectUserPermissions(String name);

    /**
     * 查询管理员权限列表
     *
     * @param name
     * @return
     */
    List<Permission> selectAdminPermissions(String name);

    /**
     * 删除权限资源依赖
     *
     * @param id
     * @return
     */
    Integer deletePermResourceDeps(Serializable id);

    /**
     * 删除权限角色依赖
     *
     * @param id
     * @return
     */
    Integer deletePermRoleDeps(Serializable id);
}
