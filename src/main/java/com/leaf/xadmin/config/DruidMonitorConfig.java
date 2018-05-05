package com.leaf.xadmin.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import lombok.Data;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;

/**
 * druid监控配置
 *
 * @author leaf
 * <p>date: 2018-02-28 16:24</p>
 * <p>version: 1.0</p>
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
@PropertySource(value = {"classpath:druid/druid.properties"})
@ConfigurationProperties(prefix = "druid")
@Data
public class DruidMonitorConfig {
    private String allow;
    private String deny;
    private String username;
    private String password;
    private String reset;

    /**
     * 访问地址: http://localhost:9000/xadmin/druid/index.html
     *
     * 注册ServletRegistrationBean
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean registrationBean() {
        ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        // IP白名单
        bean.addInitParameter("allow", allow);
        // IP黑名单(若同时满足时，deny优先于allow; 若仅满足deny，提示"Sorry, you are not permitted to view this page.")
        bean.addInitParameter("deny", deny);
        // 登录账号密码
        bean.addInitParameter("loginUsername", username);
        bean.addInitParameter("loginPassword", password);
        // 是否能够重置数据
        bean.addInitParameter("resetEnable", reset);
        return bean;
    }

    /**
     * 注册FilterRegistrationBean
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        bean.addUrlPatterns("/*");
        // 添加不需要忽略的格式信息
        bean.addInitParameter("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/datasource/*");
        return bean;
    }

    /**
     * Spring监听:
     *    1.定义拦截器
     *    2.定义切入点
     *    3.定义通知类
     *
     * @return
     */
    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    public JdkRegexpMethodPointcut druidStatPointcut() {
        JdkRegexpMethodPointcut druidStatPointcut = new JdkRegexpMethodPointcut();
        String patterns = "com.leaf.xadmin*";
        druidStatPointcut.setPatterns(patterns);
        return druidStatPointcut;
    }

    @Bean
    public Advisor druidStatAdvisor() {
        return new DefaultPointcutAdvisor(druidStatPointcut(), druidStatInterceptor());
    }
}
