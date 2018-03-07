package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.Permission;
import com.leaf.xadmin.mapper.primary.PermissionMapper;
import com.leaf.xadmin.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:41</p>
 * <p>version: 1.0</p>
 */
@Service
@Slf4j
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

    @Override
    public List<Permission> queryUserPermissions(String name) {
        return baseMapper.selectUserPermissions(name);
    }

    @Override
    public List<Permission> queryAdminPermissions(String name) {
        return baseMapper.selectAdminPermissions(name);
    }

    @Override
    public boolean addOne(Permission permission) {
        return insert(permission);
    }

    @Override
    public boolean addBatch(List<Permission> permissionList) {
        return insertBatch(permissionList);
    }

    @Override
    public Page<Permission> queryList(Page<Permission> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Permission queryOneById(Serializable id) {
        return selectById(id);
    }

    @Override
    public Permission queryOneByName(String name) {
        return selectOne(new EntityWrapper<Permission>().eq("name", name));
    }

    @Override
    public boolean updateOneById(Permission role) {
        return updateById(role);
    }
}
