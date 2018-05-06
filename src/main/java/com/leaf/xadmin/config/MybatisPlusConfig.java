package com.leaf.xadmin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import com.google.common.collect.Maps;
import com.leaf.xadmin.config.datasource.DataSourceTypeEnum;
import com.leaf.xadmin.config.datasource.DynamicDataSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * mybatis-plus配置
 *
 * @author leaf
 * <p>date: 2017-12-31 1:11</p>
 */
@Configuration
@MapperScan({"com.leaf.xadmin.mapper.*"})
public class MybatisPlusConfig {
    @Bean(name = "auth")
    @ConfigurationProperties(prefix = "spring.datasource.druid.auth")
    public DruidDataSource auth() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "bg")
    @ConfigurationProperties(prefix = "spring.datasource.druid.bg")
    public DruidDataSource bg() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "front")
    @ConfigurationProperties(prefix = "spring.datasource.druid.front")
    public DruidDataSource front() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "qrtz")
    @ConfigurationProperties(prefix = "spring.datasource.druid.qrtz")
    public DruidDataSource qrtz() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }

    @Bean
    @Profile({"dev", "test"})
    public SqlExplainInterceptor sqlExplainInterceptor() {
        SqlExplainInterceptor sqlExplainInterceptor = new SqlExplainInterceptor();
        sqlExplainInterceptor.setStopProceed(false);
        return sqlExplainInterceptor;
    }

    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        OptimisticLockerInterceptor optimisticLockerInterceptor = new OptimisticLockerInterceptor();
        return optimisticLockerInterceptor;
    }

    @Bean
    @Primary
    public DataSource multipleDataSource(@Qualifier(value = "auth") DataSource auth,
                                        @Qualifier(value = "bg") DataSource bg,
                                        @Qualifier(value = "front") DataSource front,
                                        @Qualifier(value = "qrtz") DataSource qrtz) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        HashMap<Object, Object> map = new HashMap<>(4);
        map.put(DataSourceTypeEnum.FIRST.getName(), auth);
        map.put(DataSourceTypeEnum.SECOND.getName(), bg);
        map.put(DataSourceTypeEnum.THIRD.getName(), front);
        map.put(DataSourceTypeEnum.FOUR.getName(), qrtz);
        dynamicDataSource.setTargetDataSources(map);
        dynamicDataSource.setDefaultTargetDataSource(auth);
       return dynamicDataSource;
    }

    /**
     * 创建sqlSessionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean("sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(multipleDataSource(auth(), bg(), front(), qrtz()));
        sqlSessionFactory.setTypeAliasesPackage("com.leaf.xadmin.entity.*");
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*/*.xml"));
        sqlSessionFactory.setTypeEnumsPackage("com.leaf.xadmin.vo.enums");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                paginationInterceptor(),
                performanceInterceptor(),
                optimisticLockerInterceptor(),
                sqlExplainInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration());
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration globalConfiguration = new GlobalConfiguration();
        globalConfiguration.setIdType(2);
        globalConfiguration.setFieldStrategy(2);
        globalConfiguration.setDbColumnUnderline(true);
        globalConfiguration.setRefresh(true);
        globalConfiguration.setLogicDeleteValue("0");
        globalConfiguration.setLogicNotDeleteValue("1");
        globalConfiguration.setSqlInjector(new LogicSqlInjector());
        return globalConfiguration;
    }

}
