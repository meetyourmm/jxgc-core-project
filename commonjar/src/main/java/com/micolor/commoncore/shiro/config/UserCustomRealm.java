package com.micolor.commoncore.shiro.config;

import com.micolor.commoncore.encryption.Encodes;
import com.micolor.commoncore.shiro.domain.UserInfo;
import com.micolor.commoncore.shiro.service.MineShiroUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Title: root
 * @Package com.micolor.commoncore.shiro.config
 * @Author: GeYupeng
 * @Date: 11:52 2018/7/30
 * @Modified:
 * @Description: TODO 填写描述。
 */
@Component("userCustomRealm")
public class UserCustomRealm extends AuthorizingRealm {
    @Autowired
    MineShiroUserService mineShiroUserService;
    private static final int INTERATIONS = 1024;
    private static final String ALGORITHM = "SHA-1";


    public UserCustomRealm() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
        matcher.setHashIterations(INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    //权限资源角色
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("main:view");
        info.addStringPermission("main:edit");
        return info;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        CustomizedToken customizedToken = (CustomizedToken) token;
        Map userMap = mineShiroUserService.findByCertificate(customizedToken.getCertificateType(),customizedToken.getUsername());
        SimpleAuthenticationInfo info;
        if (userMap == null) {
            throw new UnknownAccountException();
        }else{
            byte[] salt = Encodes.decodeHex(userMap.get("csalt").toString());
            info = new SimpleAuthenticationInfo(userMap.get("cvalue"), userMap.get("cpasswod"),
                    ByteSource.Util.bytes(salt),getName());
        }
        return info;
    }
}
