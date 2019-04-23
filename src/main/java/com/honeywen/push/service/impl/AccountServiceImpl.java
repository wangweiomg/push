package com.honeywen.push.service.impl;

import com.honeywen.push.entity.Account;
import com.honeywen.push.mapper.AccountMapper;
import com.honeywen.push.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wangwei
 * @date 2019/4/20
 */
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAll() {
        return accountMapper.findAll();
    }
}
