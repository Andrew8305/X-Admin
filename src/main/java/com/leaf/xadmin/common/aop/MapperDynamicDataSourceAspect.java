package com.leaf.xadmin.common.aop;

import com.leaf.xadmin.config.datasource.DataSourceContextHolder;
import com.leaf.xadmin.config.datasource.DataSourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Mapper层实现动态数据源切换(不推荐使用)
 *
 * @author leaf
 * <p>date: 2018-03-01 20:53</p>
 * <p>version: 1.0</p>
 */
@Aspect
@Component
@Slf4j
@Deprecated
public class MapperDynamicDataSourceAspect {

    @Pointcut("execution(* com.leaf.xadmin.mapper..*.*(..))")
    private void mapperDynamicDataSourceAspect() {}

    @Before("mapperDynamicDataSourceAspect()")
    public void switchDataSource(JoinPoint point) {
        // 依据方法名进行数据源选择
        DataSourceContextHolder.setDataSourceType(determineCurrentDataSource(point.getSignature().getName()));
    }

    @After("mapperDynamicDataSourceAspect())")
    public void restoreDataSource() {
        // 清理当前数据源
        DataSourceContextHolder.clearDataSourceType();
    }

    /**
     * 确定当前数据源
     *
     * @param methodName 方法名
     * @return
     */
    private String determineCurrentDataSource(String methodName) {
        if (StringUtils.isEmpty(methodName)) {
            // 使用默认数据源
            return DataSourceTypeEnum.values()[0].getValue();
        }
        for (DataSourceTypeEnum dataSourceTypeEnum : DataSourceTypeEnum.values()) {
            String value = dataSourceTypeEnum.getValue();
            if (methodName.contains(value)) {
                return value;
            }
        }
        return DataSourceTypeEnum.values()[0].getValue();
    }
}
