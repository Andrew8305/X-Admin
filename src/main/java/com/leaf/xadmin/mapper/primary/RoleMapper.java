package com.leaf.xadmin.mapper.primary;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

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
}
