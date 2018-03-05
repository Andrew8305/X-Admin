package com.leaf.xadmin.config.datasource;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 数据源配置
 *
 * @author leaf
 * <p>date: 2017-12-30 4:59</p>
 * <p>version: 1.0</p>
 */
@Configuration
@Deprecated
public class DynamicDataSourceConfig {

    /**
     * 多数据源路由配置
     *
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource(@Qualifier(value = "primaryDataSource")DataSource primaryDataSource,
                                        @Qualifier(value = "secondDataSource")DataSource secondDataSource) {
        DynamicDataSourceRouter dynamicDataSourceRouter = new DynamicDataSourceRouter();
        dynamicDataSourceRouter.setDefaultTargetDataSource(primaryDataSource);
        HashMap<Object, Object> map = Maps.newHashMap();
        map.put(DataSourceTypeEnum.PRIMARY.getValue(), primaryDataSource);
        map.put(DataSourceTypeEnum.SECOND.getValue(), secondDataSource);
        dynamicDataSourceRouter.setTargetDataSources(map);
        return dynamicDataSourceRouter;
    }

}

