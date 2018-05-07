package com.leaf.xadmin.service.auth;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.auth.Permission;
import com.leaf.xadmin.entity.auth.Resource;
import com.leaf.xadmin.entity.auth.Role;

import java.io.Serializable;
import java.sql.SQLDataException;
import java.util.List;
import java.util.Set;

/**
 * @author leaf
 * <p>date: 2018-02-04 20:48</p>
 * <p>version: 1.0</p>
 */
public interface IResourceService extends IService<Resource> {

    /**
     * 批量添加资源记录列表
     *
     * @param resourceList
     * @return
     */
    boolean addBatch(List<Resource> resourceList);

    /**
     * 添加单个资源
     *
     * @param resource
     * @return
     */
    boolean addOne(Resource resource);

    /**
     * 批量添加或更新资源记录列表
     *
     * @param resourceList
     * @return
     */
    boolean addOrUpdateBatch(List<Resource> resourceList);

    /**
     * 根据路径查询资源信息
     *
     * @param path
     * @return
     * @throws SQLDataException
     */
    Resource queryOneByPath(String path);

    /**
     * 查询资源列表
     *
     * @param page
     * @return
     */
    Page<Resource> queryList(Page<Resource> page);

    /**
     * 查询指定id资源信息
     *
     * @param id
     * @return
     */
    Resource queryOneById(Serializable id);


    /**
     * 根据路径查询角色信息列表
     *
     * @param path
     * @return
     */
    Set<Role> queryRolesByPath(String path);

    /**
     * 更新资源信息
     *
     * @param resource
     * @return
     */
    boolean updateOne(Resource resource);

    /**
     * 删除资源信息
     *
     * @param id
     * @return
     */
    boolean deleteOne(Serializable id);
}
