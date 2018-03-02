package com.leaf.xadmin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author leaf
 * <p>date: 2018-01-05 18:53</p>
 */
@Mapper
@Repository
public interface AdminMapper extends BaseMapper<Admin> {
}
