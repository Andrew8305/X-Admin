package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.entity.auth.Permission;
import com.leaf.xadmin.service.auth.IPermissionService;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.form.PermissionSubmitForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leaf
 * <p>date: 2018-03-06 12:58</p>
 * <p>version: 1.0</p>
 */
@Api(value = "权限请求相关", description = "/perm")
@RestController
@RequestMapping("perm")
@CrossOrigin
@Slf4j
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    @ApiOperation(value = "获取权限列表")
    @GetMapping(value = "getAll")
    public ResponseResultVO getAll(@RequestParam("current") int current,
                                   @RequestParam("size") int size) {
        Page<Permission> page = new Page<>(current, size);
        return ResponseResultUtil.success(permissionService.queryList(page));
    }

    @ApiOperation(value = "添加新权限")
    @PutMapping(value = "add")
    public ResponseResultVO add(@Valid PermissionSubmitForm permissionSubmitForm) {
        Permission permission = Permission.builder().build();
        BeanUtils.copyProperties(permissionSubmitForm, permission);
        return ResponseResultUtil.success(permissionService.addOne(permission));
    }

    @ApiOperation(value = "更新权限信息")
    @PostMapping(value = "update")
    public ResponseResultVO update(@Valid PermissionSubmitForm permissionSubmitForm) {
        Permission permission = Permission.builder().build();
        BeanUtils.copyProperties(permissionSubmitForm, permission);
        return ResponseResultUtil.success(permissionService.updateOne(permission));
    }

    @ApiOperation(value = "删除权限信息")
    @DeleteMapping(value = "delete")
    public ResponseResultVO delete(@RequestParam("id") String id) {
        return ResponseResultUtil.success(permissionService.deleteOne(id));
    }

}
