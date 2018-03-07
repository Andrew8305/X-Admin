package com.leaf.xadmin.config.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.leaf.xadmin.config.MybatisPlusConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * @author leaf
 * <p>date: 2018-03-07 18:46</p>
 * <p>version: 1.0</p>
 */
@Configuration
@EnableTransactionManagement
@PropertySource(value = {"classpath:druid/druid.properties"})
@MapperScan(basePackages = "com.leaf.xadmin.mapper.auth", sqlSessionTemplateRef = "fourSqlSessionTemplate")
public class FourDataBaseConfig {
    @Bean(name = "fourDataSource")
    @ConfigurationProperties(prefix = "four")
    public DruidDataSource customDataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean
    public PlatformTransactionManager customTransactionManager(@Qualifier("fourDataSource")DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "fourSqlSessionTemplate")
    public SqlSessionTemplate customSqlSessionTemplate(@Qualifier("fourSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(value = "fourSqlSessionFactory")
    public SqlSessionFactory customSqlSessionFactory(@Qualifier(value = "fourDataSource") DataSource dataSource) throws Exception {
        return MybatisPlusConfig.createSqlSessionFactory(dataSource);
    }
}
