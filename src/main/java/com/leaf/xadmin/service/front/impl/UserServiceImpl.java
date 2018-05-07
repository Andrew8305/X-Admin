package com.leaf.xadmin.service.front.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.front.Account;
import com.leaf.xadmin.entity.front.User;
import com.leaf.xadmin.mapper.front.UserMapper;
import com.leaf.xadmin.service.front.IAccountService;
import com.leaf.xadmin.service.front.IUserService;
import com.leaf.xadmin.utils.encrypt.PassEncryptUtil;
import com.leaf.xadmin.utils.generator.SnowflakeUtil;
import com.leaf.xadmin.vo.enums.ErrorStatus;
import com.leaf.xadmin.vo.enums.LoginType;
import com.leaf.xadmin.vo.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.io.Serializable;
import java.util.Random;

/**
 * @author leaf
 * <p>date: 2017-12-31 2:22</p>
 */
@Service
@CacheConfig(cacheNames = "users")
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Autowired
    private PassEncryptUtil passEncryptUtil;

    @Autowired
    private IAccountService accountService;

    @Override
    public boolean register(User user) {
        Random random = new Random();
        SnowflakeUtil idWorker = new SnowflakeUtil(random.nextInt(31), random.nextInt(31));

        if (!ObjectUtils.isEmpty(user)) {
            User oneByName = queryOneByName(user.getName());
            if (oneByName != null) {
                throw new GlobalException(ErrorStatus.ACCOUNT_EXIST_ERROR);
            }
            // 生成id
            String id = String.valueOf(idWorker.nextId());
            // 设置密钥
            passEncryptUtil.setSecretKey(LoginType.USER.getValue() + user.getName());
            user.setId(id);
            user.setPass(passEncryptUtil.encryptPass(user.getPass()));
            if (addOne(user)) {
                Account account = Account.builder()
                        .id(id)
                        .email(user.getEmail())
                        .phone(user.getPhone())
                        .nickname(user.getName())
                        .build();
                return accountService.addOne(account);
            } else {
                throw new GlobalException(ErrorStatus.SQL_EXECUTE_ERROR);
            }
        }

        return false;

    }

    @Cacheable(key = "#p0")
    @Override
    public User login(String name) {
        User u = selectOne(new EntityWrapper<User>().eq("name", name));
        if (u != null) {
            preLogin(name);
            return u;
        }

        return null;
    }

    @CacheEvict(key = "#p0", allEntries = true)
    @Override
    public void logout(String name) {
        preLogout(name);
    }

    @Override
    public boolean addOne(User user) {
        return insert(user);
    }

    @Override
    public User queryOneById(Serializable id) {
        return selectById(id);
    }

    @Override
    public User queryOneByName(String name) {
        return selectOne(new EntityWrapper<User>().eq("name", name));
    }

    @Override
    public Page<User> queryList(Page<User> page) {
        return page.setRecords(selectList(new EntityWrapper<>()));
    }

    @Override
    public Page<User> queryListByType(Page<User> page, Integer type) {
        return page.setRecords(selectList(new EntityWrapper<User>().eq("value", type)));
    }

    @Override
    public Page<User> queryListByStatus(Page<User> page, Integer status) {
        return page.setRecords(selectList(new EntityWrapper<User>().eq("status", status)));
    }

    /**
     * 预登录操作
     *
     * @param name
     */
    private void preLogin(String name) {
        log.debug("用户" + name + "进行预登录!");
        // TODO 预登录操作
    }

    /**
     * 预登出操作
     *
     * @param name
     */
    private void preLogout(String name) {
        log.debug("用户" + name + "进行预登出!");
        // TODO 预登出操作
    }
}
