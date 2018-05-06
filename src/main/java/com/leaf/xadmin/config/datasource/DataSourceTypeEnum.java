package com.leaf.xadmin.config.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author leaf
 * <p>date: 2018-02-28 12:35</p>
 * <p>version: 1.0</p>
 */
@Getter
@AllArgsConstructor
public enum DataSourceTypeEnum {
    // 数据源枚举
    FIRST(1, "AUTH", "权限数据库"),
    SECOND(2, "BG", "后台数据库"),
    THIRD(3, "FRONT", "前台数据库"),
    FOUR(4, "QRTZ", "QUARTZ数据库");

    private Integer id;
    private String name;
    private String desc;
}
