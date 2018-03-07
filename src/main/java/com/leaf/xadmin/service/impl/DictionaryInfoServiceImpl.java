package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.DictionaryInfo;
import com.leaf.xadmin.mapper.primary.DictInfoMapper;
import com.leaf.xadmin.service.IDictionaryInfoService;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:22</p>
 * <p>version: 1.0</p>
 */
@Service
public class DictionaryInfoServiceImpl extends ServiceImpl<DictInfoMapper, DictionaryInfo> implements IDictionaryInfoService {
    @Override
    public Page<DictionaryInfo> queryList(Page<DictionaryInfo> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Page<DictionaryInfo> queryListByDictionaryType(Page<DictionaryInfo> page, Long dictionaryType) {
        return page.setRecords(selectList(new EntityWrapper<DictionaryInfo>().eq("dictionary_type", dictionaryType)));
    }

    @Override
    public boolean addOne(DictionaryInfo dictionaryInfo) {
        return insert(dictionaryInfo);
    }

    @Override
    public boolean updateOne(DictionaryInfo dictionaryInfo) {
        return updateById(dictionaryInfo);
    }

    @Override
    public boolean deleteOne(Serializable id) {
        return deleteById(id);
    }
}
