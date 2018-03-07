package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.constants.GlobalConstants;
import com.leaf.xadmin.entity.Resource;
import com.leaf.xadmin.service.IResourceService;
import com.leaf.xadmin.utils.request.RequestMappingResolveUtil;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.RequestResourceVO;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.form.ResourceSubmitForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.PatternMatchUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author leaf
 * <p>date: 2018-03-06 13:28</p>
 * <p>version: 1.0</p>
 */
@Api(value = "资源请求相关", description = "/resource")
@RestController
@RequestMapping("resource")
@CrossOrigin
@Slf4j
public class ResourceController {

    @Autowired
    private RequestMappingHandlerMapping requestMappingHandlerMapping;
    @Autowired
    private RequestMappingResolveUtil requestMappingResolveUtil;
    @Autowired
    private IResourceService resourceService;

    @ApiOperation(value = "持久化所有资源列表")
    @PostMapping(value = "saveAllResources")
    public ResponseResultVO saveAllResources() {
        List<Resource> resourceList = new ArrayList<>();
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            for (String next : m.getKey().getPatternsCondition().getPatterns()) {
                if (PatternMatchUtils.simpleMatch(GlobalConstants.REQUEST_PATH_PATTERN_MATH, next)) {
                    HandlerMethod method = m.getValue();
                    List<RequestResourceVO> requestResourceVOS = requestMappingResolveUtil.methodResolver(method.getMethod());
                    Resource resource = RequestMappingResolveUtil.pathMergeUpgrade(requestResourceVOS);
                    resourceList.add(resource);
                }
            }
        }

        return ResponseResultUtil.success(resourceService.addBatch(resourceList));
    }

    @ApiOperation(value = "获取资源列表")
    @GetMapping(value = "getAll")
    public ResponseResultVO getAll(@RequestParam("current") int current,
                                   @RequestParam("size") int size) {
        Page<Resource> page = new Page<>(current, size);
        return ResponseResultUtil.success(resourceService.queryList(page));
    }

    @ApiOperation(value = "更新资源信息")
    @PostMapping(value = "update")
    public ResponseResultVO update(@Valid ResourceSubmitForm resourceSubmitForm) {
        Resource resource = Resource.builder().build();
        BeanUtils.copyProperties(resourceSubmitForm, resource);
        return ResponseResultUtil.success(resourceService.updateOne(resource));
    }
}
