package com.leaf.xadmin.mapper.primary;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.leaf.xadmin.entity.DictionaryType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:14</p>
 * <p>version: 1.0</p>
 */
@Mapper
@Repository
public interface DictTypeMapper extends BaseMapper<DictionaryType> {

}
