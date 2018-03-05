package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.Role;
import com.leaf.xadmin.mapper.primary.RoleMapper;
import com.leaf.xadmin.service.IRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
