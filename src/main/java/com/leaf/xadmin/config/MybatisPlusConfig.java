package com.leaf.xadmin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 *
 * @author leaf
 * <p>date: 2017-12-31 1:11</p>
 */
@Configuration
@MapperScan(basePackages = "com.leaf.xadmin.mapper")
public class MybatisPlusConfig {

}
