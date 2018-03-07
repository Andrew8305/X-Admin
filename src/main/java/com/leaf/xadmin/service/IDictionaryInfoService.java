package com.leaf.xadmin.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.leaf.xadmin.entity.DictionaryInfo;

import java.io.Serializable;

/**
 * @author leaf
 * <p>date: 2018-03-06 15:17</p>
 * <p>version: 1.0</p>
 */
public interface IDictionaryInfoService extends IService<DictionaryInfo> {

    /**
     * 查询字典信息列表
     *
     * @param page
     * @return
     */
    Page<DictionaryInfo> queryList(Page<DictionaryInfo> page);

    /**
     * 查询指定类型字典信息列表
     *
     * @param page
     * @param dictionaryType
     * @return
     */
    Page<DictionaryInfo> queryListByDictionaryType(Page<DictionaryInfo> page, Long dictionaryType);

    /**
     * 添加新字典信息
     *
     * @param dictionaryInfo
     * @return
     */
    boolean addOne(DictionaryInfo dictionaryInfo);

    /**
     * 更新字典信息
     *
     * @param dictionaryInfo
     * @return
     */
    boolean updateOne(DictionaryInfo dictionaryInfo);

    /**
     * 删除字典信息
     *
     * @param id
     * @return
     */
    boolean deleteOne(Serializable id);
}
