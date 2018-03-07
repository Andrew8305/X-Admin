package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.DictionaryType;
import com.leaf.xadmin.mapper.primary.DictTypeMapper;
import com.leaf.xadmin.service.IDictionaryTypeService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:20</p>
 * <p>version: 1.0</p>
 */
@Service
public class DictionaryTypeServiceImpl extends ServiceImpl<DictTypeMapper, DictionaryType> implements IDictionaryTypeService {

    @Override
    public Page<DictionaryType> queryList(Page<DictionaryType> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public boolean addOne(DictionaryType dictionaryType) {
        return insert(dictionaryType);
    }

    @Override
    public boolean updateOne(DictionaryType dictionaryType) {
        return updateById(dictionaryType);
    }

    @Override
    public boolean deleteOne(Serializable id) {
        return deleteById(id);
    }
}
