package com.leaf.xadmin.config.datasource;

/**
 * 上下文数据源
 *
 * @author leaf
 * <p>date: 2018-02-28 12:35</p>
 * <p>version: 1.0</p>
 */
@Deprecated
public class DataSourceContextHolder {
    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     *
     * @param dataSourceTypeEnum
     */
    public static void setDataSourceType(String dataSourceTypeEnum) {
        CONTEXT_HOLDER.set(dataSourceTypeEnum);
    }

    /**
     * 取得当前数据源
     *
     * @return
     */
    public static String getDataSourceType() {
        return CONTEXT_HOLDER.get();
    }

    /**
     * 清除上下文数据源
     */
    public static void clearDataSourceType() {
        CONTEXT_HOLDER.remove();
    }
}
