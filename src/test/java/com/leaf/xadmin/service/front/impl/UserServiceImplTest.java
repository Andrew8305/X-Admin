package com.leaf.xadmin.service.front.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.entity.front.User;
import com.leaf.xadmin.service.front.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author leaf
 * <p>date: 2018-01-01 18:05</p>
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;

    @Test
    public void queryUser() {
        User user = userService.queryOneByName("leaf");
        log.info(user.toString());
    }

    @Test
    public void queryList() {
        Page<User> page = new Page<>(1, 3);
        page = userService.queryList(page);
        log.info("分页信息:{}" + page);
    }
}