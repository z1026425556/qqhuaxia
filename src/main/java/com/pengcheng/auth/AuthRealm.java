package com.pengcheng.auth;

import com.pengcheng.domain.auth.Permission;
import com.pengcheng.domain.auth.Role;
import com.pengcheng.domain.auth.SysToken;
import com.pengcheng.domain.auth.User;
import com.pengcheng.service.IShiroService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

//自定义Realm
@Component
public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private IShiroService shiroService;

    //授权：获取用户的角色和权限
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1、从PrincipalCollection中获取用户的登录信息
        User user = (User) principalCollection.getPrimaryPrincipal();
        //Integer userId = user.getUserId();
        //2、添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for(Role role : user.getRoles()){
            //2.1、添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            for(Permission permission : role.getPermissions()){
                //2.1.1、添加权限
                simpleAuthorizationInfo.addStringPermission(permission.getPermission());
            }
        }
        return simpleAuthorizationInfo;
    }

    //认证，判断token的有效性
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //获取token，即前端传入的token
        String accessToken = (String) token.getPrincipal();
        //1、根据accessToken查询用户信息
        SysToken sysToken = shiroService.findByToken(accessToken);
        //2、token失效
        if(sysToken == null || sysToken.getExpireTime().isBefore(LocalDateTime.now())){
            throw new IncorrectCredentialsException("token失效，请重新登录！");
        }
        //3、调用数据库的方法，从数据库中查询username对应的用户记录
        User user = shiroService.findByUserId(sysToken.getUserId());
        //4、若用户不存在，则可以抛出 UnknownAccountException 异常
        if(user == null){
            throw new UnknownAccountException("用户不存在！");
        }
        //5、根据用户的情况，来构建 AuthenticationInfo 对象并返回，通常使用的实现类为：SimpleAuthenticationInfo
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, accessToken, this.getName());
        return info;
    }
}









































