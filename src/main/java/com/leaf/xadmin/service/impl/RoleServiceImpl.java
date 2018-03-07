package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.Role;
import com.leaf.xadmin.mapper.primary.RoleMapper;
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
    public List<Role> queryUserRoles(String name) {
        return baseMapper.selectUserRoles(name);
    }

    @Override
    public List<Role> queryAdminRoles(String name) {
        return baseMapper.selectAdminRoles(name);
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
    public boolean updateOneById(Role role) {
        return updateById(role);
    }

}
