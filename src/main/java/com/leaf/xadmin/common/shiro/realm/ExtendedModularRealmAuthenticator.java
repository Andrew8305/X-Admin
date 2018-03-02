package com.leaf.xadmin.common.shiro.realm;

import com.leaf.xadmin.common.shiro.token.ExtendedUsernamePasswordToken;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author leaf
 * <p>date: 2018-02-06 22:38</p>
 * <p>version: 1.0</p>
 */
public class ExtendedModularRealmAuthenticator extends ModularRealmAuthenticator {

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 判断getRealms()是否返回为空
        assertRealmsConfigured();
        // 强制转换回自定义的ExtendedUsernamePasswordToke
        ExtendedUsernamePasswordToken extendedToken = (ExtendedUsernamePasswordToken) authenticationToken;
        // 登录类型
        String loginType = extendedToken.getLoginType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        Collection<Realm> typeRealms = new ArrayList<>();
        for (Realm realm : realms) {
            if (realm.getName().contains(loginType)) {
                typeRealms.add(realm);
            }
        }

        // 判断是单Realm还是多Realm
        if (typeRealms.size() == 1) {
            return doSingleRealmAuthentication(typeRealms.iterator().next(), extendedToken);
        } else {
            return doMultiRealmAuthentication(typeRealms, extendedToken);
        }
    }
}
