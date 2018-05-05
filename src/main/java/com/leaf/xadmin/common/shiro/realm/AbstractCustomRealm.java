package com.leaf.xadmin.common.shiro.realm;

import com.leaf.xadmin.vo.enums.LoginType;
import org.apache.shiro.realm.AuthorizingRealm;

/**
 * @author leaf
 * <p>date: 2018-05-04 21:08</p>
 * <p>version: 1.0</p>
 */
public abstract class AbstractCustomRealm extends AuthorizingRealm  {
    LoginType loginType = LoginType.DEFAULT;

    LoginType getLoginType() {
        return loginType;
    }
}
