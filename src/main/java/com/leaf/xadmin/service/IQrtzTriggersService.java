package com.leaf.xadmin.service;

import com.leaf.xadmin.entity.qrtz.QrtzTriggers;

import java.util.List;

/**
 * @author leaf
 * <p>date: 2018-03-01 23:39</p>
 * <p>version: 1.0</p>
 */
public interface IQrtzTriggersService {
    /**
     * 查询triggers列表
     *
     * @return
     */
    List<QrtzTriggers> queryList();
}
