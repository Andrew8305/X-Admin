package com.leaf.xadmin.service.front.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.leaf.xadmin.entity.front.Account;
import com.leaf.xadmin.mapper.front.AccountMapper;
import com.leaf.xadmin.service.front.IAccountService;
import com.leaf.xadmin.vo.enums.ErrorStatus;
import com.leaf.xadmin.vo.exception.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

/**
 * @author leaf
 * <p>date: 2018-02-08 23:41</p>
 * <p>version: 1.0</p>
 */
@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements IAccountService {

    @Override
    public boolean addOne(Account account) {
        if (!ObjectUtils.isEmpty(account) && !StringUtils.isEmpty(account.getId())) {
            Account selectOne = baseMapper.selectOne(Account.builder().id(account.getId()).build());
            if (selectOne != null) {
                throw new GlobalException(ErrorStatus.ACCOUNT_EXIST_ERROR);
            }
            // 初始化账户
            account = preAccountInit(account);
            return insert(account);
        }
        return false;
    }


    /**
     * 账户初始化
     */
    private Account preAccountInit(Account account) {
        log.debug("用户" + account.getNickname() + "账户初始化!");
        // TODO 待完善账户其他基本信息
        return account;
    }

}
