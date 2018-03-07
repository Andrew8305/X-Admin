package com.leaf.xadmin.mapper.front;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.front.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author leaf
 * <p>date: 2017/9/15 20:31</p>
 */
@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    /**
     * 查询指定用户名、密码用户
     *
     * @param name
     * @param pass
     * @return
     */
    User selectOneByNameAndPass(@Param("name") String name, @Param("pass") String pass);

    /**
     * 查询指定用户名用户
     *
     * @param name
     * @return
     */
    User selectOneByName(String name);

    /**
     * 查询指定id用户
     *
     * @param id
     * @return
     */
    User selectOneById(String id);

    /**
     * 查询所有用户列表
     *
     * @return
     */
    List<User> selectAll();

    /**
     * 查询指定状态用户列表
     *
     * @param status
     * @return
     */
    List<User> selectListByStatus(Integer status);
}
