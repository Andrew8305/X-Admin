package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.entity.Role;
import com.leaf.xadmin.service.IRoleService;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.form.RoleSubmitForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leaf
 * <p>date: 2018-03-06 12:57</p>
 * <p>version: 1.0</p>
 */
@Api(value = "角色请求相关", description = "/role")
@RestController
@RequestMapping("role")
@CrossOrigin
@Slf4j
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @ApiOperation(value = "获取角色列表")
    @GetMapping(value = "getAll")
    public ResponseResultVO getAll(@RequestParam("current") int current,
                                   @RequestParam("size") int size) {
        Page<Role> page = new Page<>(current, size);
        return ResponseResultUtil.success(roleService.queryList(page));
    }

    @ApiOperation(value = "添加新角色")
    @PutMapping(value = "add")
    public ResponseResultVO add(@Valid RoleSubmitForm roleSubmitForm) {
        Role role = Role.builder().build();
        BeanUtils.copyProperties(roleSubmitForm, role);
        return ResponseResultUtil.success(roleService.addOne(role));
    }

    @ApiOperation(value = "更新角色信息")
    @PostMapping(value = "update")
    public ResponseResultVO update(@Valid RoleSubmitForm roleSubmitForm) {
        Role role = Role.builder().build();
        BeanUtils.copyProperties(roleSubmitForm, role);
        return ResponseResultUtil.success(roleService.updateOneById(role));
    }
}
