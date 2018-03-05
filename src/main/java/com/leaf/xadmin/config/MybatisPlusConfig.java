package com.leaf.xadmin.config;

import com.baomidou.mybatisplus.MybatisConfiguration;
import com.baomidou.mybatisplus.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.entity.GlobalConfiguration;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.plugins.PerformanceInterceptor;
import com.baomidou.mybatisplus.plugins.SqlExplainInterceptor;
import com.baomidou.mybatisplus.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * mybatis-plus配置
 *
 * @author leaf
 * <p>date: 2017-12-31 1:11</p>
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * 创建sqlSessionFactory
     *
     * @param dataSource
     * @return
     * @throws Exception
     */
    public static SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
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
        GlobalConfiguration globalConfiguration = new GlobalConfiguration(new LogicSqlInjector());
        globalConfiguration.setIdType(2);
        globalConfiguration.setFieldStrategy(2);
        globalConfiguration.setDbColumnUnderline(true);
        globalConfiguration.setRefresh(true);
        globalConfiguration.setLogicDeleteValue("0");
        globalConfiguration.setLogicNotDeleteValue("1");
        sqlSessionFactory.setGlobalConfig(globalConfiguration);
        return sqlSessionFactory.getObject();
    }

}
