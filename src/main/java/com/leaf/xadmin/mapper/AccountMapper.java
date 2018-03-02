package com.leaf.xadmin.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author leaf
 * <p>date: 2018-02-08 23:42</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface AccountMapper extends BaseMapper<Account> {
}
