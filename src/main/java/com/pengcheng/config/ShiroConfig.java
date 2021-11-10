package com.pengcheng.config;

import com.pengcheng.auth.AuthFilter;
import com.pengcheng.auth.AuthRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    @Bean("shiroFilter")
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){

        ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
        shiroFilter.setSecurityManager(securityManager);
        //auth过滤
        Map<String, Filter> filters = new HashMap<String, Filter>();
        filters.put("auth", new AuthFilter());
        shiroFilter.setFilters(filters);
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //anno：所有人都可以访问，auth：只有有权限(登陆过)的才可以访问
        filterMap.put("/css/*", "anon");
        filterMap.put("/font/*", "anon");
        filterMap.put("/image/*", "anon");
        filterMap.put("/js/*", "anon");
        filterMap.put("/js/*/*", "anon");
        filterMap.put("/favicon.ico", "anon");
        filterMap.put("/html/loginLogout/register.html", "anon");
        filterMap.put("/html/loginLogout/login.html", "anon");
        filterMap.put("/html/loginLogout/resetPwd.html", "anon");
        filterMap.put("/user/isHavingInviteCode", "anon");
        filterMap.put("/user/isExistUser", "anon");
        filterMap.put("/user/register", "anon");
        //除了以上路径，其他都需要权限验证
        filterMap.put("/**", "auth");
        shiroFilter.setFilterChainDefinitionMap(filterMap);
        return shiroFilter;

    }

    @Bean("securityManager")
    public SecurityManager securityManager(AuthRealm authRealm){

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(authRealm);
        securityManager.setRememberMeManager(null);
        return securityManager;

    }

    @Bean("lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

}










































