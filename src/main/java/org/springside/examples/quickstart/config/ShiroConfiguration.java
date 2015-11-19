package org.springside.examples.quickstart.config;

import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.shiro.realm.AuthorizingRealm;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springside.examples.quickstart.service.account.AccountService;
import org.springside.examples.quickstart.service.account.ShiroDbRealm;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfiguration{

    private static Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();

    @Bean(name = "securityManager")
    @Autowired
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthorizingRealm realm) {
        DefaultWebSecurityManager dwsm = new DefaultWebSecurityManager();
        dwsm.setRealm(realm);
        dwsm.setCacheManager(getEhCacheManager());
        return dwsm;
    }

    @Bean(name = "realm")
    @Autowired
    public AuthorizingRealm getShiroRealm(@Qualifier("accountService") AccountService accountService) {
        ShiroDbRealm shiroDbRealm=new ShiroDbRealm();
        shiroDbRealm.setAccountService(accountService);
        return shiroDbRealm;
    }

    @Bean(name = "shiroFilter")
    @Autowired
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean
                .setSecurityManager(securityManager);
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setSuccessUrl("/");
        filterChainDefinitionMap.put("/login", "authc");
        filterChainDefinitionMap.put("/logout", "logout");
        filterChainDefinitionMap.put("/static/**","anon");
        filterChainDefinitionMap.put("/api/**","anon");
        filterChainDefinitionMap.put("/register/**","anon");
        filterChainDefinitionMap.put("/admin/**","roles[admin]");
        filterChainDefinitionMap.put("/**","user");

        shiroFilterFactoryBean
                .setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean(name = "shiroEhcacheManager")
    public EhCacheManager getEhCacheManager() {
        EhCacheManager em = new EhCacheManager();
        em.setCacheManagerConfigFile("classpath:ehcache/ehcache-shiro.xml");
        return em;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    @Autowired
    public AuthorizationAttributeSourceAdvisor getAuthorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return new AuthorizationAttributeSourceAdvisor();
    }



}
