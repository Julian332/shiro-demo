package org.julian.demo.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.julian.demo.shiro.shiro.UserRealm;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Description: shiro 配置类
 * Project: shiro
 *
 * @author : Julian
 * Create Time:2019/12/5 10:07
 */
@Configuration
public class ShiroConfig {
    //创建shiroFilterFactoryBean
    //defaultWebSecurityManager
    //realm

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

//        /****
//         *添加内置过滤器
//         * anon: 无需认证
//         * authc: 需要登录
//         * user:  实现记住我/rememberMe
//         * perms: 需要资源授权
//         * role: 需要角色
//         */

        Map<String, String> filterMap = new LinkedHashMap<>();
        shiroFilterFactoryBean.setLoginUrl("/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("/login");
        filterMap.put("/*", "authc");
        filterMap.put("/add","perms[use:add]");
        filterMap.put("/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    DefaultWebSecurityManager defaultWebSecurityManager(UserRealm userRealm) {
        return new DefaultWebSecurityManager(userRealm);
    }

    @Bean
    public UserRealm userRealm() {
        return new UserRealm();
    }
}
