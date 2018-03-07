package com.leaf.xadmin.mapper.bg;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.bg.DictionaryInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:15</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface DictInfoMapper extends BaseMapper<DictionaryInfo> {

}
