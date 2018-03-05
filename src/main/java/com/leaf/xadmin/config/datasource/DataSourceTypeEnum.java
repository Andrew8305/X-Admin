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
@Deprecated
public enum DataSourceTypeEnum {
    // 数据源枚举
    PRIMARY("primary"), SECOND("second");
    private String value;
}
