package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.Permission;
import com.leaf.xadmin.mapper.primary.PermissionMapper;
import com.leaf.xadmin.service.IPermissionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
