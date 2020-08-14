package com.jie.config;

import com.jie.shior.UserRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ShiroConfig {

    // Create a filter to intercept all requests
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        // Set a security manager for the filter
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        return shiroFilterFactoryBean;
    }

    // Create Security Manager
    @Bean
    public DefaultWebSecurityManager defaultWebSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();

        // Hand over the customized realm to SecurityManager for management
        defaultWebSecurityManager.setRealm(realm());
        // Use cookie management object remember me
        defaultWebSecurityManager.setRememberMeManager(rememberMeManager());

        return defaultWebSecurityManager;
    }

    @Bean
    public Realm realm() {
        UserRealm userRealm = new UserRealm();
        return userRealm;
    }


    @Bean
    public SimpleCookie rememberMeCookie(){
        // The parameter is the name of the cookie, corresponding to the name of the front-end checkbox = rememberMe
        SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
        // 60 days
        simpleCookie.setMaxAge(5184000);
        return simpleCookie;
    }

    // Cookie management object remember me
    private CookieRememberMeManager rememberMeManager(){
        CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
        cookieRememberMeManager.setCookie(rememberMeCookie());
        // Used to set the encryption key, parameter type byte[], byte array length requirement 16
        cookieRememberMeManager.setCipherKey("EVANNIGHTLY_WAOU".getBytes());
        return cookieRememberMeManager;
    }


}
