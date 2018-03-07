package com.leaf.xadmin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.bg.DictionaryType;

import java.io.Serializable;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:17</p>
 * <p>version: 1.0</p>
 */
public interface IDictionaryTypeService extends IService<DictionaryType> {

    /**
     * 查询字典类型列表
     *
     * @param page
     * @return
     */
    Page<DictionaryType> queryList(Page<DictionaryType> page);

    /**
     * 添加字典类型
     *
     * @param dictionaryType
     * @return
     */
    boolean addOne(DictionaryType dictionaryType);

    /**
     * 更新字典类型
     *
     * @param dictionaryType
     * @return
     */
    boolean updateOne(DictionaryType dictionaryType);

    /**
     * 删除字典类型
     *
     * @param id
     * @return
     */
    boolean deleteOne(Serializable id);
}
