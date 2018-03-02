package com.leaf.xadmin.config.datasource;

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
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

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
@EnableTransactionManagement
@PropertySource(value = {"classpath:druid/druid.properties"})
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

    /**
     * 数据源一
     *
     * @return
     */
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "primary")
    public DruidDataSource primaryDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 数据源二
     *
     * @return
     */
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "second")
    public DruidDataSource secondDataSource() {
        return DruidDataSourceBuilder.create().build();
    }


    @Bean
    public PlatformTransactionManager primaryTransactionManager(@Qualifier("primaryDataSource")DataSource primaryDataSource) {
        return new DataSourceTransactionManager(primaryDataSource);
    }

    @Bean
    public PlatformTransactionManager secondTransactionManager(@Qualifier("secondDataSource")DataSource secondDataSource) {
        return new DataSourceTransactionManager(secondDataSource);
    }

    @Primary
    @Bean(value = "mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory1(@Qualifier(value = "primaryDataSource") DataSource dataSource,
                                                GlobalConfiguration globalConfiguration) throws Exception {
        return createSqlSessionFactory(dataSource, globalConfiguration);
    }

    @Bean(value = "mybatisSqlSession")
    public SqlSessionFactory sqlSessionFactory2(@Qualifier(value = "secondDataSource") DataSource dataSource,
                                                GlobalConfiguration globalConfiguration) throws Exception {
        return createSqlSessionFactory(dataSource, globalConfiguration);
    }

    /**
     * 创建sqlSessionFactory
     *
     * @param dataSource
     * @param globalConfiguration
     * @return
     * @throws Exception
     */
    private SqlSessionFactory createSqlSessionFactory(DataSource dataSource,
                                                      GlobalConfiguration globalConfiguration) throws Exception {
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setTypeAliasesPackage("com.leaf.xadmin.entity, com.leaf.xadmin.constants");
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));
        sqlSessionFactory.setTypeEnumsPackage("com.leaf.xadmin.vo.enums");
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setCacheEnabled(false);
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        sqlSessionFactory.setConfiguration(configuration);
        sqlSessionFactory.setPlugins(new Interceptor[]{
                new PaginationInterceptor(),
                new PerformanceInterceptor(),
                new OptimisticLockerInterceptor(),
                new SqlExplainInterceptor()
        });
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

    @Bean
    public GlobalConfiguration globalConfiguration() {
        GlobalConfiguration conf = new GlobalConfiguration(new LogicSqlInjector());
        conf.setIdType(2);
        conf.setFieldStrategy(2);
        conf.setDbColumnUnderline(true);
        conf.setRefresh(true);
        conf.setLogicDeleteValue("0");
        conf.setLogicNotDeleteValue("1");
        return conf;
    }
}

