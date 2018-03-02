package com.leaf.xadmin.service;

/**
 * @author leaf
 * <p>date: 2018-02-27 22:38</p>
 * <p>version: 1.0</p>
 */
public interface IQuartzService {
    /**
     * 添加新任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    void add(String jobClassName, String jobGroupName, String cronExpression);
    /**
     * 暂停任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    void pause(String jobClassName, String jobGroupName);
    /**
     * 继续任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    void resume(String jobClassName, String jobGroupName);

    /**
     * 重新安排任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @param cronExpression
     * @throws Exception
     */
    void reschedule(String jobClassName, String jobGroupName, String cronExpression);

    /**
     * 删除任务
     *
     * @param jobClassName
     * @param jobGroupName
     * @throws Exception
     */
    void delete(String jobClassName, String jobGroupName);

}
