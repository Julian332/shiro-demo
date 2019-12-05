package org.julian.demo.shiro.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Description: TODO
 * Project: shiro
 *
 * @author : Julian
 * Create Time:2019/12/5 10:10
 */
@Slf4j
public class UserRealm extends AuthorizingRealm {
    private static final Logger logger = LoggerFactory.getLogger(UserRealm.class);
    public static Map<String, String> map = new HashMap<>(10);

    /***
     * Description 执行授权逻辑
     * @author julian cao
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @param principalCollection wu
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.debug("授权逻辑开始");
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addStringPermission("user:add");
        return simpleAuthorizationInfo;
    }

    /***
     * Description 执行认证逻辑
     * @author julian cao
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @param authenticationToken wu
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        map.put("julian", "123456");
        map.put("juKian", "123456");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        if (!map.containsKey(usernamePasswordToken.getUsername())) {
            return null;
        }
        String password = map.get(usernamePasswordToken.getUsername());
        return new SimpleAuthenticationInfo("", password, "");
//        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
//        while (iterator.hasNext()) {
//            Map.Entry<String, String> entry = iterator.next();
//            if (entry.getKey().equals(usernamePasswordToken.getUsername()) && entry.getValue().equals(String.valueOf(usernamePasswordToken.getPassword()))) {
//
//            }
//        }
    }
}
