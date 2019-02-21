package com.micolor.commoncore.shiro.config;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @Title: root
 * @Package com.micolor.commoncore.shiro.config
 * @Author: GeYupeng
 * @Date: 10:38 2018/7/30
 * @Modified:
 * @Description: TODO 填写描述。
 */
public class CustomizedModularRealmAuthenticator extends ModularRealmAuthenticator {
    /**
     * Attempts to authenticate the given token by iterating over the internal collection of
     * {@link Realm}s.  For each realm, first the {@link Realm#supports(AuthenticationToken)}
     * method will be called to determine if the realm supports the {@code authenticationToken} method argument.
     * <p/>
     * If a realm does support
     * the token, its {@link Realm#getAuthenticationInfo(AuthenticationToken)}
     * method will be called.  If the realm returns a non-null account, the token will be
     * considered authenticated for that realm and the account data recorded.  If the realm returns {@code null},
     * the next realm will be consulted.  If no realms support the token or all supporting realms return null,
     * an {@link AuthenticationException} will be thrown to indicate that the user could not be authenticated.
     * <p/>
     * After all realms have been consulted, the information from each realm is aggregated into a single
     * {@link AuthenticationInfo} object and returned.
     *
     * @param authenticationToken the token containing the authentication principal and credentials for the
     *                            user being authenticated.
     * @return account information attributed to the authenticated user.
     * @throws IllegalStateException   if no realms have been configured at the time this method is invoked
     * @throws AuthenticationException if the user could not be authenticated or the user is denied authentication
     *                                 for the given principal and credentials.
     */
    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken) throws AuthenticationException {
        assertRealmsConfigured();
        CustomizedToken customizedToken = (CustomizedToken) authenticationToken;
        String certificateType = customizedToken.getCertificateType();
        Collection<Realm> realms = getRealms();
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(certificateType))
                typeRealms.add(realm);
        }
        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1){
            return doSingleRealmAuthentication(typeRealms.iterator().next(), customizedToken);
        }else{
            return doMultiRealmAuthentication(typeRealms, customizedToken);
        }


    }
}
