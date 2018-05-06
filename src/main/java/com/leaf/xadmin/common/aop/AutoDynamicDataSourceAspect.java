package com.leaf.xadmin.common.aop;

import com.leaf.xadmin.config.datasource.DataSourceContextHolder;
import com.leaf.xadmin.config.datasource.DataSourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

/**
 * @author leaf
 * <p>date: 2018-05-06 19:18</p>
 * <p>version: 1.0</p>
 */
@Aspect
@Order(-10)
@Component
@Slf4j
public class AutoDynamicDataSourceAspect {
    private static final String IMPL = "impl";

    @Pointcut("execution(* com.leaf.xadmin.service..*.*(..))")
    public void autoDynamicDataSourceAspect() {}

    @Before("autoDynamicDataSourceAspect()")
    public void switchDataSource(JoinPoint point) {
        // 依据包名进行数据源选择
        DataSourceContextHolder.setDataSourceType(determineCurrentDataSource(point));
    }

    @After("autoDynamicDataSourceAspect()")
    public void restoreDataSource() {
        // 清理当前数据源
        DataSourceContextHolder.clearDataSourceType();
    }

    /**
     * 确定当前数据源
     *
     * @param jp 方法名
     * @return
     */
    private String determineCurrentDataSource(JoinPoint jp) {
        if (ObjectUtils.isEmpty(jp)) {
            return "";
        }
        int i = 5;
        String[] split = jp.getTarget().getClass().getName().split("\\.");
        String name = "";
        if (IMPL.equalsIgnoreCase(split[i])) {
             name = split[i - 1];
        }
        for (DataSourceTypeEnum dataSourceTypeEnum : DataSourceTypeEnum.values()) {
            String value = dataSourceTypeEnum.getName();
            if (value.equalsIgnoreCase(name)) {
                return value;
            }
        }
        return "";
    }
}
