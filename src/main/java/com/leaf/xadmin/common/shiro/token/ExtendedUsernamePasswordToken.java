package com.leaf.xadmin.common.shiro.token;

import com.leaf.xadmin.vo.enums.LoginType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.shiro.authc.UsernamePasswordToken;

/**
 * @author leaf
 * <p>date: 2018-02-06 22:35</p>
 * <p>version: 1.0</p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ExtendedUsernamePasswordToken extends UsernamePasswordToken {
    private LoginType loginType;

    public ExtendedUsernamePasswordToken(final String username, final String password, LoginType loginType) {
        super(username, password);
        this.loginType = loginType;
    }
}
