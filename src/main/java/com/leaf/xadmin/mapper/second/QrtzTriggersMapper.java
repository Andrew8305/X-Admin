package com.leaf.xadmin.mapper.second;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.qrtz.QrtzTriggers;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author leaf
 * <p>date: 2018-03-01 23:37</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface QrtzTriggersMapper extends BaseMapper<QrtzTriggers> {
}
