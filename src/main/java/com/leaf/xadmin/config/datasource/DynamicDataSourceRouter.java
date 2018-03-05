package com.leaf.xadmin.config.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 继承AbstractRoutingDataSource，实现简单动态数据源切换
 *
 * @author leaf
 * <p>date: 2018-02-28 12:35</p>
 * <p>version: 1.0</p>
 */
@Deprecated
public class DynamicDataSourceRouter extends AbstractRoutingDataSource {

    /**
     * 取得当前使用数据源
     *
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}

