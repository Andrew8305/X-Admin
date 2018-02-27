package com.leaf.xadmin.utils.request;

import com.leaf.xadmin.controller.UserController;
import com.leaf.xadmin.vo.RequestResourceVO;
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
public class RequestMappingResolveUtilTest {

    @Autowired
    private RequestMappingResolveUtil resolveUtil;

    @Test
    public void methodResolver() throws Exception {
        resolveUtil.methodResolver(UserController.class.getMethod("getUser", String.class));
    }

    @Test
    public void pathMerge() throws Exception {
        List<RequestResourceVO> resourceList = resolveUtil.methodResolver(UserController.class.getMethod("getUser", String.class));
        RequestResourceVO requestResourceVO = resourceList.get(0);
        String pathMerge = RequestMappingResolveUtil.pathMerge(requestResourceVO.getParentPath(), requestResourceVO.getChildPath());
        log.debug(pathMerge);
    }

}