package com.leaf.xadmin.common.aop;

import com.leaf.xadmin.config.datasource.DataSourceContextHolder;
import com.leaf.xadmin.common.annotations.TargetDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Service层注解动态数据源切换
 *
 * @author leaf
 * <p>date: 2018-03-01 21:00</p>
 * <p>version: 1.0</p>
 */
@Aspect
@Order(-1)
@Component
@Slf4j
@Deprecated
public class AnnotationDynamicDataSourceAspect {

    @Pointcut("@annotation(com.leaf.xadmin.common.annotations.TargetDataSource)")
    public void annotationDynamicDataSourceAspect() { }

    @Before("annotationDynamicDataSourceAspect()")
    public void changeDataSource(JoinPoint jp) {
        TargetDataSource targetAnnotation = getTargetAnnotation(jp, TargetDataSource.class);
        if (!ObjectUtils.isEmpty(targetAnnotation)) {
            DataSourceContextHolder.setDataSourceType(targetAnnotation.name().getValue());
        }
    }

    @After("annotationDynamicDataSourceAspect()")
    public void restoreDataSource() {
        DataSourceContextHolder.clearDataSourceType();
    }

    /**
     * 获取指定注解
     *
     * @param jp 切入点
     * @return
     */
    private <T extends Annotation> T getTargetAnnotation(JoinPoint jp, Class<T> clazz ) {
        T annotation = null;
        // 获取目标类对象
        Class<?> target = jp.getTarget().getClass();
        // 获取目标类对象中的所有方法
        Method[] targetMethods = target.getDeclaredMethods();

        // 获取被切入点方法名
        String methodName = jp.getSignature().getName();
        // 获取被切入点参数类型
        Object[] args = jp.getArgs();

        for (Method method : targetMethods) {
            // 比较筛选同名方法
            if (methodName.equals(method.getName())) {
                Class<?>[] parameterTypes = method.getParameterTypes();
                // 比较同名方法中参数列表长度是否一致
                if (parameterTypes.length == args.length) {
                    annotation = method.getAnnotation(clazz);
                }
            }
        }

        return annotation;
    }


}
