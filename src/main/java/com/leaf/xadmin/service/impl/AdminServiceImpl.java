package com.leaf.xadmin.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.leaf.xadmin.common.shiro.utils.ShiroHelpUtil;
import com.leaf.xadmin.constants.GlobalConstants;
import com.leaf.xadmin.entity.Admin;
import com.leaf.xadmin.mapper.primary.AdminMapper;
import com.leaf.xadmin.service.IAdminService;
import com.leaf.xadmin.utils.encrypt.PassEncryptUtil;
import com.leaf.xadmin.utils.generator.SnowflakeUtil;
import com.leaf.xadmin.vo.enums.ErrorStatus;
import com.leaf.xadmin.vo.enums.LoginType;
import com.leaf.xadmin.vo.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author leaf
 * <p>date: 2018-01-05 18:35</p>
 */
@Service
@Slf4j
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private PassEncryptUtil passEncryptUtil;
    @Autowired
    private ShiroHelpUtil shiroHelpUtil;

    @Override
    public Admin queryOneByName(String name) {
        return selectOne(new EntityWrapper<Admin>().eq("name", name));
    }

    @Override
    public Page<Admin> queryList(Page<Admin> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Page<Admin> queryListByType(Page<Admin> page, Integer type) {
        return page.setRecords(selectList(new EntityWrapper<Admin>().eq("type", type)));
    }

    @Override
    public Page<Admin> queryListByStatus(Page<Admin> page, Integer status) {
        return page.setRecords(selectList(new EntityWrapper<Admin>().eq("status", status)));
    }

    @Override
    public boolean addOne(Admin admin) {
        Random random = new Random();
        SnowflakeUtil idWorker = new SnowflakeUtil(random.nextInt(31), random.nextInt(31));

        if (!ObjectUtils.isEmpty(admin)) {
            Admin oneByName = queryOneByName(admin.getName());
            if (oneByName != null) {
                throw new GlobalException(ErrorStatus.ACCOUNT_EXIST_ERROR);
            }
            // 生成id
            String id = String.valueOf(idWorker.nextId());
            // 设置密钥
            passEncryptUtil.setSecretKey(LoginType.ADMIN.getValue() + admin.getName());
            admin.setId(id);
            admin.setPass(passEncryptUtil.encryptPass(admin.getPass()));
            return insert(admin);
        }

        return false;
    }

    @Override
    public Page<Session> querySessionsByLoginType(Page<Session> page, LoginType loginType) {
        // 过滤掉部分session
        List<Session> sessionList = shiroHelpUtil.getActiveSessions().stream().filter(session -> {
            String type = (String) session.getAttribute(GlobalConstants.SESSION_LOGIN_TYPE_KEY);
            return loginType.getValue().equalsIgnoreCase(type);
        }).collect(Collectors.toList());
        return page.setRecords(sessionList);
    }

    @Override
    public boolean forceLogoutByName(String name) {
        return shiroHelpUtil.removeLoginSessionByName(name);
    }

    @Override
    public boolean clearAuthorsByName(String name) {
        return shiroHelpUtil.removeAuthorsByName(name);
    }
}
