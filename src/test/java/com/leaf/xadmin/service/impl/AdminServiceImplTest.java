package com.leaf.xadmin.service.impl;

import com.leaf.xadmin.entity.Admin;
import com.leaf.xadmin.service.IAdminService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AdminServiceImplTest {

    @Autowired
    private IAdminService adminService;

    @Test
    public void queryOne() {
        Admin admin = adminService.queryOne("admin");
        assertNotNull(admin);
    }
}