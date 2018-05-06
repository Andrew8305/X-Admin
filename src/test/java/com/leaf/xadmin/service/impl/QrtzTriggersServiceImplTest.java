package com.leaf.xadmin.service.impl;

import com.leaf.xadmin.entity.qrtz.QrtzTriggers;
import com.leaf.xadmin.service.qrtz.IQrtzTriggersService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class QrtzTriggersServiceImplTest {

    @Autowired
    private IQrtzTriggersService qrtzTriggersService;

    @Test
    public void queryList() {
        List<QrtzTriggers> qrtzTriggers = qrtzTriggersService.queryList();
        log.info("列表数:{}", qrtzTriggers.size());
    }
}