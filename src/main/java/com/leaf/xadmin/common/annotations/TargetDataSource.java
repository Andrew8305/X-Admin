package com.leaf.xadmin.common.annotations;

import com.leaf.xadmin.config.datasource.DataSourceTypeEnum;
import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 指定目标数据源
 *
 * @author leaf
 * <p>date: 2018-03-01 21:15</p>
 * <p>version: 1.0</p>
 */
@Target({ ElementType.METHOD, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TargetDataSource {
    DataSourceTypeEnum type() default DataSourceTypeEnum.SECOND;
}
