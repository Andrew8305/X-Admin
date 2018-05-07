package com.leaf.xadmin.service.auth.impl;

import com.leaf.xadmin.entity.auth.Resource;
import com.leaf.xadmin.service.auth.IResourceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ResourceServiceImplTest {

    @Autowired
    private IResourceService resourceService;

    @Test
    public void addOne() {
        Resource resource = Resource.builder().name("sss").desc("sss").path("/sss").deleteFlag(0).type(1).build();
        boolean b = resourceService.addOne(resource);
        log.debug(String.valueOf(b));
    }
}