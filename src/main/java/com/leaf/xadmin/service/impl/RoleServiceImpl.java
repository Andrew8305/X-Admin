package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.auth.Role;
import com.leaf.xadmin.mapper.auth.RoleMapper;
import com.leaf.xadmin.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:40</p>
 * <p>version: 1.0</p>
 */
@Service
@Slf4j
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Override
    public List<Role> queryUserRoles(Serializable id) {
        return baseMapper.selectUserRoles(id);
    }

    @Override
    public List<Role> queryAdminRoles(Serializable id) {
        return baseMapper.selectAdminRoles(id);
    }

    @Override
    public boolean addOne(Role role) {
        return insert(role);
    }

    @Override
    public boolean addBatch(List<Role> roleList) {
        return insertBatch(roleList);
    }

    @Override
    public Page<Role> queryList(Page<Role> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Role queryOneById(Serializable id) {
        return selectById(id);
    }

    @Override
    public Role queryOneByName(String name) {
        return selectOne(new EntityWrapper<Role>().eq("name", name));
    }

    @Override
    public boolean updateOne(Role role) {
        return updateById(role);
    }

    @Override
    public boolean deleteOne(Serializable id) {
        // 解除角色相关依赖
        baseMapper.deleteAdminRoleDeps(id);
        baseMapper.deleteResourceRoleDeps(id);
        baseMapper.deleteUserRoleDeps(id);
        baseMapper.deletePermRoleDeps(id);

        // TODO 强制系统相关权限刷新

        return deleteById(id);
    }

}
