package com.leaf.xadmin.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.leaf.xadmin.common.shiro.token.ExtendedUsernamePasswordToken;
import com.leaf.xadmin.constants.GlobalConstants;
import com.leaf.xadmin.entity.front.User;
import com.leaf.xadmin.service.front.IUserService;
import com.leaf.xadmin.utils.jwt.JwtUtil;
import com.leaf.xadmin.utils.response.ResponseResultUtil;
import com.leaf.xadmin.vo.ResponseResultVO;
import com.leaf.xadmin.vo.enums.ErrorStatus;
import com.leaf.xadmin.vo.enums.LoginType;
import com.leaf.xadmin.vo.exception.GlobalException;
import com.leaf.xadmin.vo.form.UserRegisterForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @author leaf
 * <p>date: 2017-12-31 1:47</p>
 */
@Api(value = "用户请求相关", description = "/user")
@RestController
@RequestMapping("user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @ApiOperation(value = "用户登录")
    @PostMapping(value = "login")
    public ResponseResultVO login(@RequestParam("name") String name, @RequestParam("pass") String pass, HttpServletResponse response) {
        String loginToken; // 登录凭证
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            ExtendedUsernamePasswordToken token = new ExtendedUsernamePasswordToken(name, pass, LoginType.USER);
            subject.login(token);
            subject.getSession().setAttribute(GlobalConstants.SESSION_LOGIN_TYPE_KEY, LoginType.USER.getValue());
            User user = userService.login(name);
            if (user != null) {
                loginToken = JwtUtil.generateToken(user.getId(), name, user.getPass());
                // token写入cookie
                response.addCookie(new Cookie("token", loginToken));
                return ResponseResultUtil.success(loginToken);
            }
            throw new GlobalException(ErrorStatus.LOGIN_FAIL_ERROR);
        } else {
            logout();
            return login(name, pass, response);
        }
    }

    @ApiOperation(value = "用户退出")
    @PostMapping(value = "logout")
    public ResponseResultVO logout() {
        // 清除自定义用户信息缓存
        userService.logout(SecurityUtils.getSubject().getPrincipal().toString());
        // 清除用户权限缓存
        SecurityUtils.getSubject().logout();
        return ResponseResultUtil.success(Boolean.TRUE);
    }

    @ApiOperation(value = "用户注册")
    @PutMapping(value = "register")
    public ResponseResultVO register(@Valid UserRegisterForm userRegisterForm) {
        User user = User.builder().build();
        BeanUtils.copyProperties(userRegisterForm, user);
        return ResponseResultUtil.success(userService.register(user));
    }

    @ApiOperation(value = "获取指定id用户信息")
    @GetMapping(value = "getUser/{id}")
    public ResponseResultVO getUserById(@Validated @PathVariable("id") String id) {
        return ResponseResultUtil.success(userService.queryOneById(id));
    }

    @ApiOperation(value = "获取指定用户名信息")
    @GetMapping(value = "getUser")
    public ResponseResultVO getUserByName(@RequestParam("name") String name) {
        return ResponseResultUtil.success(userService.queryOneByName(name));
    }

    @ApiOperation(value = "获取全部用户列表")
    @GetMapping(value = "getAll")
    public ResponseResultVO getAll(@RequestParam("current") int current,
                                   @RequestParam("size") int size) {
        Page<User> page = new Page<>(current, size);
        return ResponseResultUtil.success(userService.queryList(page));
    }

    @ApiOperation(value = "获取指定类型用户列表")
    @GetMapping(value = "getListByType")
    public ResponseResultVO getListByType(@RequestParam("current") int current,
                                          @RequestParam("size") int size,
                                          @RequestParam("type") Integer type) {
        Page<User> page = new Page<>(current, size);
        return ResponseResultUtil.success(userService.queryListByType(page, type));
    }

    @ApiOperation(value = "获取指定状态用户列表")
    @GetMapping(value = "getListByStatus")
    public ResponseResultVO getListByStatus(@RequestParam("current") int current,
                                            @RequestParam("size") int size,
                                            @RequestParam("status") Integer status) {
        Page<User> page = new Page<>(current, size);
        return ResponseResultUtil.success(userService.queryListByStatus(page, status));
    }
}
