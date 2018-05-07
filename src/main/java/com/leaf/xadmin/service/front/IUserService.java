package com.leaf.xadmin.service.front;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.front.User;

import java.io.Serializable;

/**
 * @author leaf
 * <p>date: 2017-12-31 1:47</p>
 */
public interface IUserService extends IService<User> {

    /**
     * 注册新用户
     *
     * @param user 用户信息
     * @return true/false(注册成功/注册失败)
     */
    boolean register(User user);

    /**
     * 用户登录
     *
     * @param name 用户名
     * @return 返回登录用户信息
     */
    User login(String name);

    /**
     * 用户登出
     *
     * @param name 用户名
     * @return void
     */
    void logout(String name);

    /**
     * 添加单个用户
     *
     * @param user
     * @return true/false(添加成功/添加失败)
     */
    boolean addOne(User user);

    /**
     * 查询指定id信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User queryOneById(Serializable id);

    /**
     * 查询指定用户名信息
     *
     * @param name 用户名
     * @return 用户信息
     */
    User queryOneByName(String name);

    /**
     * 查询用户列表
     *
     * @param page 分页信息
     * @return 分页用户信息
     */
    Page<User> queryList(Page<User> page);

    /**
     * 查询指定类型用户列表
     *
     * @param page 分页信息
     * @param type 用户类型
     * @return 分页用户信息
     */
    Page<User> queryListByType(Page<User> page, Integer type);

    /**
     * 查询指定状态用户列表
     *
     * @param page 分页信息
     * @param status 用户状态
     * @return 分页用户信息
     */
    Page<User> queryListByStatus(Page<User> page, Integer status);

}
