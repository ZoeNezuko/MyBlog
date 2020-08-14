package com.jie.shior;

import com.jie.model.User;
import com.jie.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    // Get authorization information
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        return simpleAuthorizationInfo;
    }

    // Get authentication information, that is, obtain the password from the database according to the user name in the token
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String Username = token.getPrincipal().toString();
        User user = userService.getByName(Username);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String Password = user.getPassword();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(Username, Password, getName());
        SecurityUtils.getSubject().getSession().setAttribute("uid",user.getId());
        return authenticationInfo;
    }
}
