package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.constants.GlobalConstants;
import com.leaf.xadmin.entity.bg.Admin;
import com.leaf.xadmin.vo.dto.AdminInfoDTO;
import com.leaf.xadmin.vo.enums.LoginType;
import com.leaf.xadmin.service.bg.IAdminService;
import com.leaf.xadmin.common.shiro.token.ExtendedUsernamePasswordToken;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.form.AdminRegisterForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author leaf
 * <p>date: 2018-01-21 15:57</p>
 * <p>version: 1.0</p>
 */
@Api(value = "管理员相关请求", description = "/admin")
@RestController
@RequestMapping("admin")
@CrossOrigin
@Slf4j
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value = "管理员登录")
    @PostMapping(value = "login")
    public ResponseResultVO login(@RequestParam("name") String name, @RequestParam("pass") String pass) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            ExtendedUsernamePasswordToken token = new ExtendedUsernamePasswordToken(name, pass, LoginType.ADMIN);
            subject.login(token);
            // 添加登录类型标识
            subject.getSession().setAttribute(GlobalConstants.SESSION_LOGIN_TYPE_KEY, LoginType.ADMIN.getValue());
            Admin admin = adminService.queryOneByName(name);
            AdminInfoDTO adminInfoDTO = AdminInfoDTO.builder().build();
            BeanUtils.copyProperties(admin, adminInfoDTO);
            return ResponseResultUtil.success(adminInfoDTO);
        } else {
            // 退出，重新登录
            logout();
            return login(name, pass);
        }
    }

    @ApiOperation(value = "用户退出")
    @PostMapping(value = "logout")
    public ResponseResultVO logout() {
        // 清除用户权限缓存
        SecurityUtils.getSubject().logout();
        return ResponseResultUtil.success(Boolean.TRUE);
    }

    @ApiOperation(value = "管理员注册")
    @PutMapping(value = "register")
    public ResponseResultVO register(@Valid AdminRegisterForm adminRegisterForm) {
        Admin admin = Admin.builder().build();
        BeanUtils.copyProperties(adminRegisterForm, admin);
        return ResponseResultUtil.success(adminService.addOne(admin));
    }

    @ApiOperation(value = "获取管理员在线会话列表")
    @GetMapping(value = "getAdminSessions")
    public ResponseResultVO getAdminSessions(@RequestParam("current") int current,
                                      @RequestParam("size") int size) {
        Page<Session> page = new Page<>(current, size);
        return ResponseResultUtil.success(adminService.querySessionsByLoginType(page, LoginType.ADMIN));
    }

    @ApiOperation(value = "获取用户在线会话信息列表")
    @GetMapping(value = "getUserSessions")
    public ResponseResultVO getUserSessions(@RequestParam("current") int current,
                                      @RequestParam("size") int size) {
        Page<Session> page = new Page<>(current, size);
        return ResponseResultUtil.success(adminService.querySessionsByLoginType(page, LoginType.USER));
    }

    @ApiOperation(value = "强制指定用户下线")
    @PostMapping(value = "forceLogoutByName")
    public ResponseResultVO forceLogoutByName(@RequestParam("name") String name) {
        return ResponseResultUtil.success(adminService.forceLogoutByName(name));
    }

    @ApiOperation(value = "清理指定用户权限缓存")
    @PostMapping(value = "clearAuthorsByName")
    public ResponseResultVO clearAuthorsByName(@RequestParam("name") String name) {
        return ResponseResultUtil.success(adminService.clearAuthorsByName(name));
    }

    @ApiOperation(value = "查询所有管理员")
    @GetMapping(value = "getAll")
    public ResponseResultVO getAll(@RequestParam("current") int current,
                                   @RequestParam("size") int size) {
        Page<Admin> page = new Page<>(current, size);
        return ResponseResultUtil.success(adminService.queryList(page));
    }

    @ApiOperation(value = "查询指定类型管理员列表")
    @GetMapping(value = "getListByType")
    public ResponseResultVO getAllByType(@RequestParam("current") int current,
                                   @RequestParam("size") int size,
                                   @RequestParam("type") int type) {
        Page<Admin> page = new Page<>(current, size);
        return ResponseResultUtil.success(adminService.queryListByType(page, type));
    }

    @ApiOperation(value = "查询指定状态管理员列表")
    @GetMapping(value = "getListByStatus")
    public ResponseResultVO getAllStatus(@RequestParam("current") int current,
                                         @RequestParam("size") int size,
                                         @RequestParam("status") int status) {
        Page<Admin> page = new Page<>(current, size);
        return ResponseResultUtil.success(adminService.queryListByStatus(page, status));
    }


}
