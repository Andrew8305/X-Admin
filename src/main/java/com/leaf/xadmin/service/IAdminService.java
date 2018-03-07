package com.leaf.xadmin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.google.common.collect.Lists;
import com.leaf.xadmin.entity.Admin;
import com.leaf.xadmin.vo.enums.LoginType;
import io.swagger.models.auth.In;
import org.apache.shiro.session.Session;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-01-05 18:23</p>
 */
public interface IAdminService extends IService<Admin> {

    /**
     * 查询管理员信息
     *
     * @param name
     * @return
     */
    Admin queryOneByName(String name);

    /**
     * 查询管理员列表
     *
     * @param page
     * @return
     */
    Page<Admin> queryList(Page<Admin> page);

    /**
     * 查询指定类型管理员
     *
     * @param page
     * @param type
     * @return
     */
    Page<Admin> queryListByType(Page<Admin> page, Integer type);

    /**
     * 查询指定状态管理员
     *
     * @param page
     * @param status
     * @return
     */
    Page<Admin> queryListByStatus(Page<Admin> page, Integer status);

    /**
     * 添加管理员
     *
     * @param admin
     * @return
     */
    boolean addOne(Admin admin);

    /**
     * 获取管理员session
     *
     * @param page
     * @param loginType
     * @return
     */
    Page<Session> querySessionsByLoginType(Page<Session> page, LoginType loginType);

    /**
     * 强制用户下线
     *
     * @param name
     * @return
     */
    boolean forceLogoutByName(String name);

    /**
     * 清理指定用户权限缓存
     *
     * @param name
     * @return
     */
    boolean clearAuthorsByName(String name);

}
