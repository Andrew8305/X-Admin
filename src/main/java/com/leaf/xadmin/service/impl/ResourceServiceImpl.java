package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.auth.Permission;
import com.leaf.xadmin.entity.auth.Resource;
import com.leaf.xadmin.entity.auth.Role;
import com.leaf.xadmin.mapper.auth.ResourceMapper;
import com.leaf.xadmin.service.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:59</p>
 * <p>version: 1.0</p>
 */
@Service
@Slf4j
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements IResourceService {

    @Override
    public boolean addBatch(List<Resource> resourceList) {
        return insertBatch(resourceList);
    }

    @Override
    public boolean addOrUpdateBatch(List<Resource> resourceList) {
        return insertOrUpdateBatch(resourceList);
    }

    @Override
    public Resource queryOneByPath(String path) {
        return selectOne(new EntityWrapper<Resource>().eq("path", path));
    }

    @Override
    public Page<Resource> queryList(Page<Resource> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Resource queryOneById(Serializable id) {
        return selectById(id);
    }

    @Override
    public Set<Role> queryRolesByPath(String path) {
        return baseMapper.selectResourceRolesByPath(path);
    }

    @Override
    public boolean updateOne(Resource resource) {
        return updateById(resource);
    }

    @Override
    public boolean deleteOne(Serializable id) {
        // 删除资源相关依赖
        baseMapper.deleteResourceRoleDeps(id);

        // TODO　强制系统相关权限刷新

        return deleteById(id);
    }
}
